package packages.informationmanipulation;

import packages.connectouterentity.AutoTraderDBInterface;
import packages.connectouterentity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class dedicated to returning multiple cars when accessing the database
 */
public class ReturnMultipleCars {

    /**
     * A helper method that iterates through the returned result set of a query and returns every car in dictionary
     * form and places it in an ArrayList
     * @param query The specified query
     * @return An ArrayList of HashMaps representing a list of each Car's information
     * @throws SQLException If there was a database access error
     */
    private ArrayList<HashMap<String, Object>> returnAllCarsQuery(String query) throws SQLException{

        // Establish a connection and create a set of results from that query
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        ArrayList<HashMap<String, Object>> returnList = new ArrayList<>();

        // Create a ReturnCarInformation Object to be used in the while loop
        ReturnCarInformation returnCarInfo = new ReturnCarInformation();

        // This continuously moves the cursor to the next row to reproduce data
        while (myResultSet.next()){

            // Populate that car's information
            HashMap<String, Object> carMap = returnCarInfo.populateCarMap(myResultSet);

            // Add it to the list
            returnList.add(carMap);

        }
        return returnList;
    }

    /**
     * Returns all cars available in the database
     * @return An ArrayList of all cars from the database
     * @throws SQLException If there was a database access error
     */
    public ArrayList<HashMap<String, Object>> returnAllCars() throws SQLException {

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars;";
        return returnAllCarsQuery(query);
    }

    /**
     * Returns all cars that are of a specific car type
     * @param filter The specific car type we're looking for
     * @return An ArrayList of HashMaps reprsenting the cars that matched the filter
     * @throws SQLException If there was a database access error
     */
    public ArrayList<HashMap<String, Object>> returnFilteredCars(String filter) throws SQLException {
        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE car_type = \"" + filter + "\";";

        return returnAllCarsQuery(query);
    }

}
