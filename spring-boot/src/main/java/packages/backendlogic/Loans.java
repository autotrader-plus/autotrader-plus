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

        loans = new HashMap<>();

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
            callApi();
        } catch(IOException|InterruptedException e) {
            e.printStackTrace();
            throw new SensoConnectionFailureException();
        }
    }

    /**
     * calls the SensoApi and adds the Api return values into the Loans Object
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     */
    private void callApi() throws IOException, InterruptedException{
        for (int j = 0; j < cars.size(); j++){
            Car car = cars.getCar(j);
            HashMap<String, String> mapping = makeUserInfo(car);

            SensoAPIInterface connector = new ConnectSensoRateAPI(mapping);
            loans.put(car.getID(), connector.pingSensoAPI(mapping).get("installments")); // edited
        }
    }

    /**
     * Creates a HashMap containing User and Car info for the SensoApi call
     * @param car The Car Object from Car.java
     * @return returns HashMap mapping, which is all the info required for SensoApi call
     */
    private HashMap<String, String> makeUserInfo(Car car) {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", Integer.toString(car.getPrice() -
                Integer.parseInt(buyer.getDownPayment())));
        mapping.put("credit_score", buyer.getCreditScore());
        mapping.put("payment_budget", buyer.getMonthlyBudget());
        mapping.put("vehicle_make", car.getBrand());
        mapping.put("vehicle_model", "Daniel");
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
