package com.example.backendlogic;

import com.example.connectouterentity.ConnectSensoAPI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Loans {
    private ArrayList loans;
    private CarList cars;
    private User buyer;

    public Loans(HashMap user, ArrayList<HashMap<String, String>> carlist) throws IOException, InterruptedException {
        //creates User object (buyer) based on length of given user Hashmap
        this.loans = new ArrayList();
        if (user.size() == 5){
            this.buyer = new User(Integer.parseInt((String) user.get("creditscore")),
                    Integer.parseInt((String) user.get("monthlybudget")),
                    Integer.parseInt((String) user.get("downpayment")), (String) user.get("zipcode"),
                    (String) user.get("name"));

            // gets buyer pricerange
            int budget = Integer.parseInt(this.buyer.getPriceRange());
            int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);

            // creates CarList object (cars) based on length of given carlist Arraylist
            this.cars = new CarList();
            makecars(carlist, upsold_budget);


            // preps info and calls SensoAPI
            callApi();
        }
        else {
            this.buyer = new User(Integer.parseInt((String) user.get("creditscore")),
                    Integer.parseInt((String) user.get("monthlybudget")),
                    Integer.parseInt((String) user.get("downpayment")), (String) user.get("zipcode"),
                    (String) user.get("name"), Integer.parseInt((String) user.get("monthlyincome")),
                    (String) user.get("employed") == "employed",
                    (String) user.get("homeowner") == "homeowner",
                    Integer.parseInt((String) user.get("monthlydebt")));

            // gets buyer pricerange
            int budget = Integer.parseInt(this.buyer.getPriceRange());
            int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);

            // creates CarList object (cars) based on length of given carlist Arraylist
            this.cars = new CarList();
            makecars(carlist, upsold_budget);

            // preps info and calls SensoAPI
            callApi();
        }


    }

    private void callApi() throws IOException, InterruptedException {
        for (int j = 0; j < this.cars.size(); j++){
            Car car = (Car) this.cars.getCar(j);
            HashMap<String, String> mapping = new HashMap<>();
            mapping.put("loan_amount", Integer.toString(car.getPrice() -
                    Integer.parseInt(this.buyer.getDownpayment())));
            mapping.put("credit_score", this.buyer.getCreditscore());
            mapping.put("payment_budget", this.buyer.getMonthlybudget());
            mapping.put("vehicle_make", car.getBrand());
            mapping.put("vehicle_model", "Daniel");
            mapping.put("vehicle_year", car.getYear());
            mapping.put("vehicle_kms", car.getKMS());

            ConnectSensoAPI connector = new ConnectSensoAPI(mapping);
            this.loans.add(car.getBrand() + "  " + connector.getReturnInfo());
        }
    }

    // creates CarList object (cars) based on length of given carlist Arraylist
    public void makecars(ArrayList<HashMap<String, String>> carlist, int budget) {
        for (int i = 0; i < carlist.size(); i++) {
            String cost = carlist.get(i).get("Cost");
            String year = carlist.get(i).get("Model Year");
            String brand = carlist.get(i).get("Car");
            String kms = carlist.get(i).get("Mileage");
            String cartype = carlist.get(i).get("Car Type");
            String ID = carlist.get(i).get("ID");
            String Dealership = carlist.get(i).get("Dealership");
            if (Integer.parseInt(cost) <= budget) {
                this.cars.AddtoList(new Car(year, brand, kms, cartype, ID, cost, Dealership));
            }
        }
    }
    public ArrayList getLoans(){return this.loans;}

    public CarList getCars(){return this.cars;}

    public User getBuyer(){return this.buyer;}
}
