package packages.database_info_manipulation;

import packages.connect_outer_entity.AutoTraderDBInterface;
import packages.connect_outer_entity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Represents an AuthenticateUser object used for user sign in operations.
 */
public class AuthenticateUser {

    /**
     * Checks if the user's credentials are valid
     * @return A boolean representing if the User's credentials were valid
     * @throws SQLException If there was a database access error
     */
    public boolean checkUser(String username, String password) throws SQLException {
        HashMap<String, Object> allLogins = getAllLogins(username, password);

        return !allLogins.isEmpty();
    }

    /**
     * A helper method to populate a map with the user's credentials from the database, if existent
     * @return A hashmap representing the user's credentials from the database, if they exist
     * @throws SQLException If there was a database access error
     */
    private HashMap<String, Object> getAllLogins(String username, String password) throws SQLException {
        // Writing a SQL query
        String query = "SELECT * FROM cars.Users WHERE password = '" + password +
                "' AND name = '" + username + "';";

        // Establish a connection and create a set of results from that query
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        // Creating the map
        HashMap<String, Object> returnMap = new HashMap<>();

        // This if statement moves the cursor that's within the result set
        if(myResultSet.next()) {
            returnMap = populateUserMap(myResultSet);
        }

        return returnMap;
    }

    /**
     * A helper method to populate a map with a User's login information
     * @param myResultSet The ResultSet from the specified query in getAllLogins()
     * @return A map representing the User's login information
     * @throws SQLException If there was a database access error
     */
    public HashMap<String, Object> populateUserMap(ResultSet myResultSet) throws SQLException {

        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("ID", myResultSet.getString("user_id"));
        returnMap.put("Password", myResultSet.getString("password"));
        returnMap.put("Username", myResultSet.getString("name"));

        return returnMap;
    }
}
