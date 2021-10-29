package com.example.informationmanipulation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.connectouterentity.ConnectAutoTraderDB;

public class ReturnCarInformation {

    /**
     * Will return the detail of a specified car in a legible/comprehensive format
     */
    public static String returnCarDetailsString(Integer car_id) throws SQLException {

        // Converting the dictionary with the values to a string
        HashMap<String, String> dict = returnCarDetails(car_id);


        return "ID: " + dict.get("ID") + "\n" +
                "Car: " + dict.get("Car") + "\n" +
                "Cost: $" + dict.get("Cost") + "\n" +
                "Mileage: " + dict.get("Mileage") + "\n" +
                "Model Year: " + dict.get("Model Year") + "\n" +
                "Dealership: " + dict.get("Dealership") + "\n" +
                "Car Type: " + dict.get("Car Type");
    }

    /**
     * Will return the detail of a specified car in a a dictionary format
     */
    public static HashMap<String, String> returnCarDetails(Integer car_id) throws SQLException {

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        // Creating the map
        HashMap<String, String> returnMap = new HashMap<>();

        // This if statement moves the cursor that's within the result set
        if(myResultSet.next()) {
            returnMap = populateCarMap(myResultSet);
        }

        return returnMap;
    }

    /**
     * A helper method to populate a map with a car's information
     */
    public static HashMap<String, String> populateCarMap(ResultSet myResultSet) throws SQLException {
        HashMap<String, String> returnMap = new HashMap<>();
        returnMap.put("ID", myResultSet.getString("id_car"));
        returnMap.put("Car", myResultSet.getString("name"));
        returnMap.put("Cost", myResultSet.getString("cost"));
        returnMap.put("Mileage", myResultSet.getString("mileage"));
        returnMap.put("Model Year", myResultSet.getString("model_year"));
        returnMap.put("Dealership", myResultSet.getString("dealership"));
        returnMap.put("Car Type", myResultSet.getString("car_type"));

        return returnMap;
    }

}
