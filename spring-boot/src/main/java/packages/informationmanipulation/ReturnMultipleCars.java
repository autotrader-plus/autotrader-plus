package packages.informationmanipulation;

import packages.connectouterentity.AutoTraderDBInterface;
import packages.connectouterentity.ConnectAutoTraderDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class dedicated to returning multiple cars when accessing the DB
 */
public class ReturnMultipleCars {

    /**
     * A helper method that iterates through the returned result set of a query.
     * It will return every car in dictionary form and place it into an arraylist
     */
    private static ArrayList<HashMap<String, Object>> returnAllCarsQuery(String query) throws SQLException{


        // Establish a connection and create a set of results from that query
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        ArrayList<HashMap<String, Object>> returnList = new ArrayList<>();

        // This continuously moves the cursor to the next row to reproduce data
        while (myResultSet.next()){

            // Populate that car's information
            HashMap<String, Object> carMap = ReturnCarInformation.populateCarMap(myResultSet);

            // Add it to the list
            returnList.add(carMap);

        }
        return returnList;
    }

    /**
     * Return all cars available in the database
     */
    public static ArrayList<HashMap<String, Object>> returnAllCars() throws SQLException {

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars;";
        return returnAllCarsQuery(query);
    }

    /**
     * Return all cars that are of a specific car type
     * @param filter the type of car you want to filter out
     */
    public static ArrayList<HashMap<String, Object>> returnFilteredCars(String filter) throws SQLException {
        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE car_type = \"" + filter + "\";";
        System.out.println(query);

        return returnAllCarsQuery(query);
    }

}
