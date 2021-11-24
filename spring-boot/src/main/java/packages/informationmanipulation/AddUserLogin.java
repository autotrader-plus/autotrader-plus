package packages.informationmanipulation;

import packages.backendlogic.User;
import packages.connectouterentity.AutoTraderDBInterface;
import packages.connectouterentity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUserLogin {

    /**
     * Return the currently stored max user ID plus one
     */
    static String getMaxIDAddOne() throws SQLException {
        // Writing a SQL query
        String query = "SELECT MAX(user_id) FROM cars.Users;";

        // Establish a connection and create a set of results from that query
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        int returnInt = 0;

        if(myResultSet.next()) {
            returnInt = myResultSet.getInt("MAX(user_id)");
        }
        return Integer.toString(returnInt + 1);
    }

    /**
     * Add an UserLogin and User to the database. Does not return anything.
     * @param user the user who we are recording data for. Note that this parameter is type User.
     * @param password the password chosen by the user in the front-end website
     */
    public static void addUser(User user, String password) throws SQLException {

        // Determine what userId to assign this individual
        String userID = AddUser.getMaxIDAddOne();

        // Write the SQL query
        String query = "INSERT INTO `cars`.`user_login`" +
                "( `user_id`, `user_password`, `username`)" +
                "VALUES " +
                "('" + userID +
                "', '" + password +
                "', '" + user.getName() + "');";

        // Adds a User to the database that corresponds to the newly added UserLogin
        AddUser.addUser(user, userID);

        // Establish a connection and create a set of results from that query
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        connection.exceuteQuery(query);



    }
}
