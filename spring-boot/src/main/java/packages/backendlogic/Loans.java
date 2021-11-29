package packages.backendlogic;

import packages.connectouterentity.ConnectSensoRateAPI;
import packages.connectouterentity.SensoAPIInterface;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class creates a Loans object that is a HashMap with keys corresponding to the IDs of Car Objects and values
 * corresponding to the possible car loans for each Car based on the SensoApi
 */
public class Loans implements LoanInfoInterface{
    private HashMap<String, Object> loans;
    private CarList<Car> cars;
    private User buyer;

    /**
     * Creates an empty HashMap Loans that will be storing the SensoApi return values
     * Creates an advanced or basic User Object based on the amount of info given
     * Calculates the budget of the User
     * Creates a CarList Object consisting of only cars affordable by the User based on User budget
     * Collects and organizes Car and User data for the SensoApi call
     * Stores the SensoApi return values in HashMap Loans
     * @param user The User Object from User.java
     * @param carList The CarList Object CarList.java
     */
    public Loans(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList) throws SensoConnectionFailureException {

        loans = new HashMap<String, Object>();

        //creates User object (buyer) based on length of given user Hashmap
        UserFactory userfactory = new UserFactory();
        buyer = userfactory.createUser(user);


        // gets buyer pricerange
        int budget = Integer.parseInt(buyer.getPriceRange());
        int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);

        // creates CarList object (cars) based on length of given carlist Arraylist
        cars = new CarList<>();
        makeCars(carList, upsold_budget);

        // preps info and calls SensoAPI
        try {
            constructLoansInformation();
        } catch(IOException|InterruptedException e) {
            e.printStackTrace();
            throw new SensoConnectionFailureException();
        }
    }

    /**
     * Construct a mapping of loans information and their respective approval rate
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     */
    private void constructLoansInformation() throws IOException, InterruptedException{
        for (int j = 0; j < cars.size(); j++){
            Car car = cars.getCar(j);
            HashMap<String, String> sensoRateInput = prepSensoRateAPICall(car);

            SensoAPIInterface connector = new ConnectSensoRateAPI(sensoRateInput);
            loans.put(car.getID(), connector.pingSensoAPI(sensoRateInput).get("installments"));

            ArrayList<HashMap<String, Object>> installments = (ArrayList) connector.pingSensoAPI(sensoRateInput).get("installments");
            HashMap<String, String> sensoScoreInput = prepSensoScoreAPICall(car, installments);
            String approval = calculateApprovalScore(sensoScoreInput);
            loans.put(car.getID() + " Approval Score", approval);
        }
    }

    private String calculateApprovalScore(HashMap<String, String> sensoScoreInput) throws IOException, InterruptedException {
        LoanApprovalCalculation approvalCalculator = new LoanApprovalCalculation(buyer, sensoScoreInput);
        if (Integer.parseInt(buyer.getMonthlyIncome()) == 0) {
            return approvalCalculator.getApprovalLikelihood(true); // case with basic user with no extra information
        } else{
            return approvalCalculator.getApprovalLikelihood(false); // case with advacned user with extra information
        }
    }

    /**
     * Create a Hashmap containg user and car info for the senso score api call
     * @param car - the car object
     * @return a hashmap containing all the info required to make the senso api call
     */
    private HashMap<String, String> prepSensoScoreAPICall(Car car, ArrayList<HashMap<String, Object>> installments){
        HashMap<String, String> sensoScoreInput = new HashMap<>();
        sensoScoreInput.put("balance", String.valueOf(car.getPrice()));
        sensoScoreInput.put("credit_score", buyer.getCreditScore());
        sensoScoreInput.put("loan_age", String.valueOf(installments.size()));
        sensoScoreInput.put("vehicle_year", car.getYear());
        sensoScoreInput.put("car_value", String.valueOf(car.getPrice()));
        sensoScoreInput.put("vehicle_make", car.getBrand());
        sensoScoreInput.put("vehicle_model", "Omitted"); // this field does not affect the calculation for senso api
        sensoScoreInput.put("loan_start_date", "2021-11-29"); // this is randomised as this field does not affect the calculation for senso api
        return sensoScoreInput;
    }

    /**
     * Creates a HashMap containing User and Car info for the Senso rate Api call
     * @param car The Car Object from Car.java
     * @return returns HashMap mapping, which is all the info required for SensoApi call
     */
    private HashMap<String, String> prepSensoRateAPICall(Car car) {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", Integer.toString(car.getPrice() -
                Integer.parseInt(buyer.getDownPayment())));
        mapping.put("credit_score", buyer.getCreditScore());
        mapping.put("payment_budget", buyer.getMonthlyBudget());
        mapping.put("vehicle_make", car.getBrand());
        mapping.put("vehicle_model", "Omitted"); // this field does not affect the loan calculation for senso api
        mapping.put("vehicle_year", car.getYear());
        mapping.put("vehicle_kms", car.getKMS());
        mapping.put("list_price", Integer.toString(car.getPrice()));
        mapping.put("downpayment", buyer.getDownPayment());
        return mapping;
    }

    /**
     * creates CarList object (cars) based on length of given carlist Arraylist
     */
    private void makeCars(ArrayList<HashMap<String, Object>> carlist, int budget) {
        for (HashMap<String, Object> stringStringHashMap : carlist) {
            String cost = (String) stringStringHashMap.get("Cost");
            String year = (String) stringStringHashMap.get("Model Year");
            String brand = (String) stringStringHashMap.get("Car");
            String kms = (String) stringStringHashMap.get("Mileage");
            String cartype = (String) stringStringHashMap.get("Car Type");
            String ID = (String) stringStringHashMap.get("ID");
            String Dealership = (String) stringStringHashMap.get("Dealership");
            if (Integer.parseInt(cost) <= budget) {
                cars.AddtoList(new Car(year, brand, kms, cartype, ID, cost, Dealership));
            }
        }
    }

    /**
     * The following are getter methods
     * @return returns Loans/CarList/User Object info when called
     */
    public HashMap<String, Object> getLoans(){return loans;}

    //will be used in future
    public CarList<Car> getCars(){return cars;}

    //will be used in future
    public User getBuyer(){return buyer;}

    /**
     * Overriden method for LoanInfoInterface that allows other classes to directly access this class through this
     * interface method.
     * @param user - a mapping of user info
     * @param carList - a list of cars we want the loan info for
     * @return a hashmap of cars with the loan info for the car
     */
    @Override
    public HashMap<String, Object> calculateLoans(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList) throws SensoConnectionFailureException {
        Loans loans = new Loans(user, carList);
        return loans.getLoans();
    }
}
