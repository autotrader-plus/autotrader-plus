package com.example.backendlogic;

import com.example.informationmanipulation.ReturnMultipleCars;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.lang.*;

public class test {
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
       // User bob = new User(750, 1000, 200, "M4Y111", "Bob");
       // int budget = Integer.parseInt(bob.getPriceRange());
       // int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);
        //System.out.println(upsold_budget);
        ArrayList<HashMap<String, String>> carlist = ReturnMultipleCars.returnAllCars();
        //System.out.println(carlist);
      //  ArrayList<Integer> buyable_carID = new ArrayList<Integer>();
        //made method static temporarily
        HashMap<String, String> userinfo = new HashMap();
        userinfo.put("credit-score", "770");
        userinfo.put("monthlybudget", "600");
        userinfo.put("downpayment", "200");
        userinfo.put("zip-code", "M4y111");
        userinfo.put("name", "Bob");
        Loans loan = new Loans(userinfo, carlist);
        HashMap<String, Object> output = loan.getLoans();
        System.out.println(output);

//        HashMap<String, String> userinfo2 = new HashMap();
//        userinfo2.put("credit-score", "600");
//        userinfo2.put("monthlybudget", "620");
//        userinfo2.put("downpayment", "100");
//        userinfo2.put("zip-code", "123456");
//        userinfo2.put("name", "HiHi");
//        userinfo2.put("monthlyincome", "10000");
//        userinfo2.put("employed", "employed");
//        userinfo2.put("homeowner", "homeowner");
//        userinfo2.put("monthlydebt", "3000");
//        Loans loan2 = new Loans(userinfo2, carlist);
//        ArrayList output2 = loan2.getLoans();
//        System.out.println(output2);
//        System.out.println(output2.size());

        //ArrayList succ = loan.getSuccesses();
        //CarList cars = loan.getCars();
        //System.out.println(loan.getCars());
        //System.out.println(loan.getLoans());
        /*for (int j = 0; j < cars.size(); j++){
            Car car = (Car) cars.getCar(j);
            System.out.println(car.show());
        } */
        /*
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", "10000");
        mapping.put("credit_score", "780");
        mapping.put("payment_budget", "200000");
        mapping.put("vehicle_make", "Honda");
        mapping.put("vehicle_model", "Civic");
        mapping.put("vehicle_year", "2021");
        mapping.put("vehicle_kms", "1000");

        ConnectSensoAPI connector = new ConnectSensoAPI(mapping);
        assert connector.getReturnInfo() != null; */
        //System.out.print(connector.getReturnInfo() instanceof String);
       // User hello = loan.getBuyer();
      //  System.out.println(hello.getCreditscore());
       // System.out.println(hello.getName());
        //System.out.println(carlist.size());
        //System.out.println(cars.size());
        //for (int j = 0; j < cars.size(); j++){
       //    Car car = (Car) cars.getCar(j);
      //      System.out.println(car.show());
      //  }
       /* CarList test = loan.makecars(carlist, 40000);
        for (int j = 0; j < test.size(); j++){
            Car car = (Car) test.getCar(j);
            System.out.println(car.show());
        }*/
        //System.out.println(test.size());

     //   CarList carrr = loan.getCars();
     //   for (int j = 0; j < carrr.size(); j++){
      //      Car car = (Car) carrr.getCar(j);
       //     System.out.println(car.show());
      //  }
        //System.out.println(carrr.size());

       /* CarList cars = new CarList();
        for (int i = 0; i < carlist.size(); i++){
            String year = carlist.get(i).get("Model Year");
            String brand = carlist.get(i).get("Car");
            String kms = carlist.get(i).get("Mileage");
            String cartype = carlist.get(i).get("Car Type");
            String ID = carlist.get(i).get("ID");
            String cost = carlist.get(i).get("Cost");
            String Dealership = carlist.get(i).get("Dealership");
            cars.AddtoList(i, new Car(year, brand, kms, cartype, ID, cost, Dealership));
        }
        //System.out.println("hello");
        for (int j = 0; j < cars.size(); j++){
            Car car = (Car) cars.getCar(j);
            System.out.println(car.show());
        }*/

//        ServerMainEndpointHandler.createLoanResponse(output2);

    }


}
