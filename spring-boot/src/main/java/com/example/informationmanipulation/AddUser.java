package com.example.informationmanipulation;

import com.example.connectouterentity.ConnectAutoTraderDB;
import com.example.backendlogic.User;

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

    /**
     * Add a user to the database. Does not return anything.
     * @param user the user who we are recording data for. Note that this parameter is type User.
     */
    public static void addUser(User user) throws SQLException {

        // Determine what userId to assign this individual
        String userID = AddUser.getMaxIDAddOne();

        // Write the SQL query
        String query = "INSERT INTO `cars`.`Users`" +
                "( `user_id`, `name`, `credit_score`, `location`, `max_downpayment`, `max_monthly_payment`, " +
                "`monthly_income`, `employment_status`, `homeowner`, `monthly_debt_obligations`)" +
                "VALUES " +
                "('" + userID +
                "', '" + user.getName() +
                "', '" + user.getCreditscore() +
                "', '" + user.getLocation() +
                "', '" + user.getDownpayment() +
                "', '" + user.getMonthlybudget() +
                "', '" + user.getMonthlyincome() +
                "', '" + user.isEmployed() +
                "', '" + user.isHomeowner() +
                "', '" + user.getMonthlydebt() + "');";

        // Establish a connection and create a set of results from that query
        ConnectAutoTraderDB connection = new ConnectAutoTraderDB();
        connection.exceuteQuery(query);

    }

}
