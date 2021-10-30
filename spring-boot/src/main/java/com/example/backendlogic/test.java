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
        //made method static temporarily
        System.out.println(carlist);
    }


}
