package com.example.backendlogic;
import com.example.informationmanipulation.ReturnMultipleCars;

import java.sql.SQLException;
import java.util.*;
import java.lang.*;

public class test {
    public static void main(String[] args) throws SQLException {
        User bob = new User(750, 1000, 200, "M4Y111");
        int budget = bob.getPriceRange();
        int upsold_budget = budget - (int)Math.min(budget*0.1, 2000);
        //System.out.println(upsold_budget);
        ArrayList<HashMap<String, String>> carlist = ReturnMultipleCars.returnAllCars();
        //System.out.println(carlist);
        ArrayList<Integer> buyable_carID = new ArrayList<Integer>();
        //made method static temporarily

        CarList cars = new CarList();
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
        System.out.println("hello");
        for (int j = 0; j < cars.size(); j++){
            Car car = (Car) cars.getCar(j);
            System.out.println(car.show());
        }

    }


}
