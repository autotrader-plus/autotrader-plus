package com.example.informationmanipulation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.connectouterentity.ConnectAutoTraderDB;

public class ReturnCarInformation {

    /**
     * Will return the detail of a specified car in a legible/comprehensive format
     */
    public String returnCarDetailsString(Integer car_id) throws SQLException {

        // Writing SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        // Creating the string
        String returnString = "";

        // This if statement moves the cursor that's within the result set
        if(myResultSet.next()) {
            returnString = "Car: " + myResultSet.getString("name") + "\n" +
                    "Cost: $" + myResultSet.getString("cost");
        }

        return returnString;
    }

    /**
     * Will return the detail of a specified car in a a dictionary format
     */
    public HashMap<String, Object> returnCarDetails(Integer car_id) throws SQLException {

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        // Creating the map
        HashMap<String, Object> returnMap = new HashMap<>();

        // This if statement moves the cursor that's within the result set
        if(myResultSet.next()) {
            returnMap.put("Car", myResultSet.getString("name"));
            returnMap.put("Cost", myResultSet.getString("cost"));
        }

        return returnMap;
    }

}
