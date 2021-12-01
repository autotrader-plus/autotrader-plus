package packages.backend_logic;

import packages.connect_api_db.ConnectSensoRateAPI;
import packages.connect_api_db.SensoAPIInterface;
import packages.exceptions.SensoConnectionFailureException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class creates a BasicLoansCreator object that is a HashMap with keys corresponding to the IDs of Car Objects and values
 * corresponding to the possible car loans for each Car based on the SensoApi
 */
public class BasicLoansCreator {
    private HashMap<String, Object> loans1;
    private HashMap<String, Object> loans2;
    private HashMap<String, Object> loans3;
    private CarList<Car> cars;
    private User buyer;

    /**
     * Creates an empty HashMap BasicLoansCreator that will be storing the SensoApi return values
     * Creates an advanced or basic User Object based on the amount of info given
     * Calculates the budget of the User
     * Creates a CarList Object consisting of only cars affordable by the User based on User budget
     * Collects and organizes Car and User data for the SensoApi call
     * Stores the SensoApi return values in HashMap BasicLoansCreator
     * @param user The User Object from User.java
     * @param carList The CarList Object CarList.java
     */
    public BasicLoansCreator(HashMap<String, String> user, ArrayList<HashMap<String, Object>> carList) throws SensoConnectionFailureException {
        loans1 = new HashMap<>();
        loans2 = new HashMap<>();
        loans3 = new HashMap<>();

        //creates User object (buyer) based on length of given user Hashmap
        UserFactory userfactory = new UserFactory();
        buyer = userfactory.createUser(user);

        // gets buyer pricerange for 3 year loan
        int budget = Integer.parseInt(buyer.getPriceRange(60));
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
     * calls the SensoApi and adds the Api return values into the BasicLoansCreator Object
     * @throws IOException exception thrown when failure in reading/writing/searching files
     * @throws InterruptedException exception thrown when process interrupted
     */
    private void callApi() throws IOException, InterruptedException{
        for (int j = 0; j < cars.size(); j++){
            Car car = cars.getCar(j);
            HashMap<String, String> mapping = makeUserInfo(car);
            SensoAPIInterface connector = new ConnectSensoRateAPI(mapping);
            loans1.put(car.getID(), (ArrayList<String>) connector.pingSensoAPI(mapping).get("installments"));

            //edits monthly budget for loan2
            int monthly = Integer.parseInt(buyer.getMonthlyBudget());
            monthly = (int) (monthly*0.8);
            mapping.put("payment_budget", Integer.toString(monthly));
            loans2.put(car.getID(), (ArrayList<String>) connector.pingSensoAPI(mapping).get("installments"));

            //edits monthly budget for loan3
            monthly = Integer.parseInt(buyer.getMonthlyBudget());
            monthly = (int) (monthly*0.6);
            mapping.put("payment_budget", Integer.toString(monthly));
            loans3.put(car.getID(), (ArrayList<String>) connector.pingSensoAPI(mapping).get("installments"));
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
        mapping.put("vehicle_model", "Bob Du");
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
     * @return returns BasicLoansCreator/CarList/User Object info when called
     */
    public HashMap<String, Object> getLoans1(){
        loans1.values().removeAll(Collections.singleton(null));
        return loans1;
    }

    public HashMap<String, Object> getLoans2(){
        loans2.values().removeAll(Collections.singleton(null));
        return loans2;
    }

    public HashMap<String, Object> getLoans3(){
        loans3.values().removeAll(Collections.singleton(null));
        return loans3;
    }


    public CarList<Car> getCars(){return cars;}

    public User getBuyer(){return buyer;}

}