package com.example.connectouterentity;

import java.sql.*;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class ConnectAutoTraderDB{

    /**
     * Represents a connection to AutoTraderDB.
     * Can call data in string form and Dictionary form
     */
    // This variable essentially represents the connection to the SQLDB; it holds information about that connection
    private final Statement objStatement;

    public ConnectAutoTraderDB(){
        /**
         * Initialize a Connection. We will automatically establish the DB connection at each instaitation of
         * an object.
         */
        this.objStatement = ConnectAutoTraderDB.establishConnection();
    }

    private static Statement establishConnection(){
        /**
         * Establishing a connection to the database. This method is private as it should only be accessed by the
         * initializer.
         */

        try{
            try{
                // Connecting to an API that allows us to use Java and a MySQLdb
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();

            }
            // JDBC URL. Tells Java where to find the DB to connect to. Also give username and password to
            // grant permission
            String dburl="jdbc:mysql://traderautoplusdb.cs3kq1tsgpar.ca-central-1.rds.amazonaws.com:3306/cars";
            String username="sweatless";
            String password="PaulGries123";

            // Establish and return a connection
            Connection myConnection= DriverManager.getConnection(dburl, username, password);
            return myConnection.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ResultSet writeQuery(String query) throws SQLException {
        /**
         * Write a query for the SQL database to access
         */

        return this.objStatement.executeQuery(query);

    }

    public String returnCarDetailsString(Integer car_id) throws SQLException {
        /**
         * Will return the detail of a specified car in a legible/comprehensive format
         */

        // Writing SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        // Creating a set of results from that query
        ResultSet myResultSet = writeQuery(query);

        // Creating the string
        String returnString = "";

        // This if statement moves the cursor that's within the result set
        if(myResultSet.next()) {
            returnString = "Car: " + myResultSet.getString("name") + "\n" +
                    "Cost: $" + myResultSet.getString("cost");
        }

        return returnString;
    }

    public HashMap<String, Object> returnCarDetails(Integer car_id) throws SQLException {
        /**
         * Will return the detail of a specified car in a a dictionary format
         */

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        // Creating a set of results from that query
        ResultSet myResultSet = writeQuery(query);

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

