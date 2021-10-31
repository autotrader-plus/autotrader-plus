package com.example.informationmanipulation;

import java.sql.*;

import com.example.connectouterentity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.util.HashMap;

public class ReturnUserInformation {
    /**
     * Will return the information of a user
     * @param user_id the ID of the user you are looking up
     */

    public static HashMap<String, String> returnUser(Integer user_id) throws SQLException {
        // Writing a SQL query
        String query = "SELECT * FROM cars.Users " +
                "WHERE user_id = " + user_id.toString() + ";";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        // Creating the map
        HashMap<String, String> returnMap = new HashMap<>();

        if(myResultSet.next()) {
            returnMap = populateUserMap(myResultSet);
        }
        return returnMap;
    }

    /**
     * A helper method to populate a map with a user's information
     */
    private static HashMap<String, String> populateUserMap(ResultSet myResultSet) throws SQLException {
        HashMap<String, String> returnMap = new HashMap<>();
        returnMap.put("ID", myResultSet.getString("user_id"));
        returnMap.put("Name", myResultSet.getString("name"));
        returnMap.put("Credit Score", myResultSet.getString("credit_score"));
        returnMap.put("Location", myResultSet.getString("location"));
        returnMap.put("Max Downpayment", myResultSet.getString("max_downpayment"));
        returnMap.put("Max Monthly Payment", myResultSet.getString("max_monthly_payment"));
        returnMap.put("Monthly Income", myResultSet.getString("monthly_income"));
        returnMap.put("Employment Status", myResultSet.getString("employment_status"));
        returnMap.put("Homeowner", myResultSet.getString("homeowner"));
        returnMap.put("Monthly Debt Obligation", myResultSet.getString("monthly_debt_obligations"));
        return returnMap;
    }

}
