package com.example.informationmanipulation;

import com.example.connectouterentity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUser {

    /**
     * Return the currently stored max user ID plus one
     */
    private static String getMaxIDAddOne() throws SQLException {
        // Writing a SQL query
        String query = "SELECT MAX(user_id) FROM cars.Users;";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        int returnInt = 0;

        if(myResultSet.next()) {
            returnInt = myResultSet.getInt("MAX(user_id)");
        }
        return Integer.toString(returnInt + 1);
    }

    // TODO Import user class and use instance variables to clean up long parameter list
    /**
     * Add a user to the database. Does not return anything. Note that this method is overloaded
     * @see #addUser(String, String, String, String, String, String, String, String, String)
     */
    public static void addUser(String name,
                               String creditScore,
                               String location,
                               String maxDownpayment,
                               String maxMonthlyPayment) throws SQLException {

        // Determine what userID to assign this individual
        String userID = AddUser.getMaxIDAddOne();

        // Write the SQL query
        String query = "INSERT INTO `cars`.`Users`" +
                "(`user_id`, `name`, `credit_score`, `location`, `max_downpayment`, `max_monthly_payment`," +
                "`monthly_income`, `employment_status`, `homeowner`, `monthly_debt_obligations`)" +
                " VALUES " +
                "('" + userID +
                "', '" + name +
                "', '" + creditScore +
                "', '" + location +
                "', '" + maxDownpayment +
                "', '" + maxMonthlyPayment +
                "', '', '', '', '');";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        connection.exceuteQuery(query);

    }

    // TODO Import user class and use instance variables to clean up long parameter list
    /**
     *
     * Add a user to the database. Does not return anything
     * Note that this is an overloaded method. This method specifically deals with advanced users' inputs and
     * hence has more parameters
     * @param name Name of user
     * @param creditScore creditScore of user
     * @param location location of user
     * @param maxDownpayment max down payment available for user
     * @param maxMonthlyPayment max monthly payment available for user
     * @param monthlyIncome monthly income of user
     * @param employmentStatus employment status of user
     * @param homeowner homeowner status of user
     * @param monthlyDebt monthly debt payments of user
     */
    public static void addUser(String name,
                               String creditScore,
                               String location,
                               String maxDownpayment,
                               String maxMonthlyPayment,
                               String monthlyIncome,
                               String employmentStatus,
                               String homeowner,
                               String monthlyDebt) throws SQLException {

        // Determine what userId to assign this individual
        String userID = AddUser.getMaxIDAddOne();

        // Write the SQL query
        String query = "INSERT INTO `cars`.`Users`" +
                "( `user_id`, `name`, `credit_score`, `location`, `max_downpayment`, `max_monthly_payment`, " +
                "`monthly_income`, `employment_status`, `homeowner`, `monthly_debt_obligations`)" +
                "VALUES " +
                "('" + userID +
                "', '" + name +
                "', '" + creditScore +
                "', '" + location +
                "', '" + maxDownpayment +
                "', '" + maxMonthlyPayment +
                "', '" + monthlyIncome +
                "', '" + employmentStatus +
                "', '" + homeowner +
                "', '" + monthlyDebt + "');";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        connection.exceuteQuery(query);

    }

}
