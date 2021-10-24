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
        this.objStatement = ConnectAutoTraderDB.establishConnection();
    }

    private static Statement establishConnection(){

        try{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }

            catch (ClassNotFoundException e) {
                e.printStackTrace();

            }

            String dburl="jdbc:mysql://traderautoplusdb.cs3kq1tsgpar.ca-central-1.rds.amazonaws.com:3306/cars";
            String username="sweatless";
            String password="PaulGries123";

            Connection myConnection= DriverManager.getConnection(dburl, username, password);

            return myConnection.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ResultSet writeQuery(String query) throws SQLException {

        return this.objStatement.executeQuery(query);

    }

    public String returnCarDetailsString(Integer car_id) throws SQLException {

        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        ResultSet myResultSet = writeQuery(query);

        String returnString = "";
        if(myResultSet.next()) {
            returnString = "Car: " + myResultSet.getString("name") + "\n" +
                    "Cost: $" + myResultSet.getString("cost");
        }

        return returnString;
    }

    public HashMap<String, Object> returnCarDetails(Integer car_id) throws SQLException {

        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + car_id.toString() + ";";

        ResultSet myResultSet = writeQuery(query);

        HashMap<String, Object> returnMap = new HashMap<>();
        if(myResultSet.next()) {
            returnMap.put("Car", myResultSet.getString("name"));
            returnMap.put("Cost", myResultSet.getString("cost"));
        }

        return returnMap;
    }

    public static String getContent() throws SQLException {
        ConnectAutoTraderDB t = new ConnectAutoTraderDB();
        return t.returnCarDetailsString(2);
//        System.out.println(t.returnCarDetails(2).values());
    }

}

