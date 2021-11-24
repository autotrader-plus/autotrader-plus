package packages.informationmanipulation;

import packages.connectouterentity.AutoTraderDBInterface;
import packages.connectouterentity.ConnectAutoTraderDB;
import packages.backendlogic.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUser extends AddUserLogin{


    /**
     * Add a user to the database. Does not return anything.
     * @param user the user who we are recording data for. Note that this parameter is type User.
     */
    public static void addUser(User user, String userID) throws SQLException {

        // Determine what userId to assign this individual


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
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        connection.exceuteQuery(query);

    }

}
