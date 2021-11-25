package packages.backendlogic;

import packages.connectouterentity.ConnectSensoAPI;
import packages.connectouterentity.SensoAPIInterface;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents loans available to some given cars
 */
public class Loans implements LoanInfoInterface{
    private final HashMap<String, Object> loans;
    private final CarList<Car> cars;
    private final User buyer;

    /**
     * Creates an empty HashMap Loans that will be storing the SensoApi return values
     * Creates an advanced or basic User Object based on the amount of info given
     * Calculates the budget of the User
     * Creates a CarList Object consisting of only cars affordable by the User based on User budget
     * Collects and organizes Car and User data for the SensoApi call
     * Stores the SensoApi return values in HashMap Loans
     * @param user The User Object from User.java
     * @param carlist The CarList Object CarList.java
     */
    /**
     * Creates a Loan for the specified user and the given list of cars
     * @param user The Loans are for this user
     * @param carList The list of cars we want Loans for
     * @throws SensoConnectionFailureException If failed to connect to the Senso API
     */
    public Loans(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList)
            throws SensoConnectionFailureException {

        loans = new HashMap<>();

        // creates User object (buyer) based on length of given user Hashmap
        UserFactory userFactory = new UserFactory();
        buyer = userFactory.createUser(user);


        // gets buyer price range
        int budget = Integer.parseInt(buyer.getPriceRange());
        int upsold_budget = budget - (int) Math.min(budget * 0.1, 2000);

        // creates CarList object (cars) based on length of given carList Arraylist
        cars = new CarList<>();
        makecars(carList, upsold_budget);

        // preps info and calls SensoAPI
        try {
            callApi();
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
            throw new SensoConnectionFailureException();
        }
    }

    /**
     * Calls and adds the Senso API return values into the Loans Object
     * @throws IOException If there was a failure in reading/writing/searching files
     * @throws InterruptedException If the process was interrupted
     */
    private void callApi() throws IOException, InterruptedException{
        for (int j = 0; j < cars.size(); j++){
            Car car = cars.getCar(j);
            HashMap<String, String> mapping = makeUserInfo(car);

            SensoAPIInterface connector = new ConnectSensoAPI(mapping);
            loans.put(car.getID(), connector.pingSensoAPI(mapping).get("installments"));
        }
    }

    /**
     * A helper method that creates a HashMap containing User and Car information
     * @param car The Car Object
     * @return A HashMap mapping representing all the information required for the Senso API call
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
     * Makes a CarList object based on the length of the given carList
     * @param carList The list of Car Objects to be made into a CarList Object
     * @param budget The budget of the User
     */
    private void makecars(ArrayList<HashMap<String, Object>> carList, int budget) {
        for (HashMap<String, Object> car : carList) {
            String cost = (String) car.get("Cost");
            String year = (String) car.get("Model Year");
            String brand = (String) car.get("Car");
            String kms = (String) car.get("Mileage");
            String carType = (String) car.get("Car Type");
            String ID = (String) car.get("ID");
            String dealership = (String) car.get("Dealership");
            if (Integer.parseInt(cost) <= budget) {
                cars.AddtoList(new Car(year, brand, kms, carType, ID, cost, dealership));
            }
        }
    }

    /**
     * Gets the loans
     * @return A mapping of cars to their possible loans
     */
    public HashMap<String, Object> getLoans(){
        return loans;
    }

    /**
     * Will be used in the future
     * Gets the list of Car Objects
     * @return A CarList Object representing the list of cars of interest
     */
    public CarList<Car> getCars(){
        return cars;
    }

    /**
     * Will be used in the future
     * Gets the buyer/User
     * @return A User Object representing the buyer
     */
    public User getBuyer(){
        return buyer;
    }

    /**
     * Overriden method for LoanInfoInterface that allows other classes to directly access this class through this
     * interface method.
     * @param user - A mapping of user information
     * @param carList - A list of Cars we want loan information from
     * @return A hashmap of cars representing the loan information for each car
     * @throws SensoConnectionFailureException If failed to connect to the Senso API
     */
    @Override
    public HashMap<String, Object> calculateLoans(HashMap<String, String> user, ArrayList<HashMap<String, Object>>
            carList) throws SensoConnectionFailureException {
        Loans loans = new Loans(user, carList);
        return loans.getLoans();
    }
}
