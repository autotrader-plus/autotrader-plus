package com.example.backendlogic;

import com.example.connectouterentity.ConnectSensoAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class creates a Loans object that is a HashMap with keys corresponding to the IDs of Car Objects and values
 * corresponding to the possible car loans for each Car based on the SensoApi
 */
public class Loans {
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
     * @param user
     * @param carlist
     * @throws IOException
     * @throws InterruptedException
     */
    public Loans(HashMap<String, String> user, ArrayList<HashMap<String, String>> carlist) throws IOException, InterruptedException {
        //creates User object (buyer) based on length of given user Hashmap
        this.loans = new HashMap<>();
        if (user.size() == 5){
            //create user object
            this.buyer = new User(Integer.parseInt(user.get("credit-score")),
                    Integer.parseInt( user.get("monthlybudget")),
                    Integer.parseInt( user.get("downpayment")), user.get("zip-code"),
                    user.get("name"));

            // gets buyer pricerange
            int budget = Integer.parseInt(this.buyer.getPriceRange());
            int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);

            // creates CarList object (cars) based on length of given carlist Arraylist
            this.cars = new CarList<>();
            makecars(carlist, upsold_budget);


            // preps info and calls SensoAPI
        }
        else {
            this.buyer = new User(Integer.parseInt( user.get("credit-score")),
                    Integer.parseInt( user.get("monthlybudget")),
                    Integer.parseInt( user.get("downpayment")), user.get("zip-code"),
                    user.get("name"), Integer.parseInt( user.get("monthlyincome")),
                    user.get("employed").equals("employed"),
                    user.get("homeowner").equals("homeowner"),
                    Integer.parseInt( user.get("monthlydebt")));

            // gets buyer pricerange
            int budget = Integer.parseInt(this.buyer.getPriceRange());
            int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);

            // creates CarList object (cars) based on length of given carlist Arraylist
            this.cars = new CarList<>();
            makecars(carlist, upsold_budget);

            // preps info and calls SensoAPI
        }
        callApi();
    }

    /**
     * calls the SensoApi and adds the Api return values into the Loans Object
     * @throws IOException
     * @throws InterruptedException
     */
    private void callApi() throws IOException, InterruptedException {
        for (int j = 0; j < this.cars.size(); j++){
            Car car = this.cars.getCar(j);
            HashMap<String, String> mapping = makeUserInfo(car);

            ConnectSensoAPI connector = new ConnectSensoAPI(mapping);
            this.loans.put( car.returnID() ,connector.getReturnInfo().get("installments")); // edited
        }
    }

    /**
     * Creates a HashMap containing User and Car info for the SensoApi call
     * @param car
     * @return
     */
    private HashMap<String, String> makeUserInfo(Car car) {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", Integer.toString(car.getPrice() -
                Integer.parseInt(this.buyer.getDownpayment())));
        mapping.put("credit_score", this.buyer.getCreditscore());
        mapping.put("payment_budget", this.buyer.getMonthlybudget());
        mapping.put("vehicle_make", car.getBrand());
        mapping.put("vehicle_model", "Daniel");
        mapping.put("vehicle_year", car.getYear());
        mapping.put("vehicle_kms", car.getKMS());
        mapping.put("list_price", Integer.toString(car.getPrice()));
        mapping.put("downpayment", this.buyer.getDownpayment());
        return mapping;
    }

    /**
     * creates CarList object (cars) based on length of given carlist Arraylist
     */
    private void makecars(ArrayList<HashMap<String, String>> carlist, int budget) {
        for (HashMap<String, String> stringStringHashMap : carlist) {
            String cost = stringStringHashMap.get("Cost");
            String year = stringStringHashMap.get("Model Year");
            String brand = stringStringHashMap.get("Car");
            String kms = stringStringHashMap.get("Mileage");
            String cartype = stringStringHashMap.get("Car Type");
            String ID = stringStringHashMap.get("ID");
            String Dealership = stringStringHashMap.get("Dealership");
            if (Integer.parseInt(cost) <= budget) {
                this.cars.AddtoList(new Car(year, brand, kms, cartype, ID, cost, Dealership));
            }
        }
    }

    /**
     * The following are getter methods
     * @return
     */
    public HashMap<String, Object> getLoans(){return this.loans;}

    //will be used in future
    public CarList<Car> getCars(){return this.cars;}

    //will be used in future
    public User getBuyer(){return this.buyer;}
}
