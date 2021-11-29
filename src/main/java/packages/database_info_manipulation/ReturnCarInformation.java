package packages.database_info_manipulation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import packages.connect_outer_entity.AutoTraderDBInterface;
import packages.connect_outer_entity.ConnectAutoTraderDB;

/**
 * Returns Car information from the database
 */
public class ReturnCarInformation {

    /**
     * Returns the details of a specified car in a legible/comprehensive format
     * @param carID ID number of the car
     * @return A string representing the details of the specified car
     * @throws SQLException If there was a database access error
     */
    public String returnCarDetailsString(Integer carID) throws SQLException {

        // Converting the dictionary with the values to a string
        HashMap<String, Object> dict = returnCarDetails(carID);


        return "ID: " + dict.get("ID") + "\n" +
                "Car: " + dict.get("Car") + "\n" +
                "Cost: $" + dict.get("Cost") + "\n" +
                "Mileage: " + dict.get("Mileage") + "\n" +
                "Model Year: " + dict.get("Model Year") + "\n" +
                "Dealership: " + dict.get("Dealership") + "\n" +
                "Car Type: " + dict.get("Car Type");
    }

    /**
     * Will return the detail of a specified car in a dictionary format
     */
    /**
     * Returns the detail of a specified car in a dictionary format
     * @param carID ID number of the car
     * @return A HashMap representing the details of the specified car
     * @throws SQLException If there was a database access error
     */
    public HashMap<String, Object> returnCarDetails(Integer carID) throws SQLException {

        // Writing a SQL query
        String query = "SELECT * FROM cars.AvailableCars " +
                "WHERE id_car = " + carID.toString() + ";";

        // Establish a connection and create a set of results from that query
        AutoTraderDBInterface connection = new ConnectAutoTraderDB();
        ResultSet myResultSet = connection.writeQuery(query);

        // Creating the map
        HashMap<String, Object> returnMap = new HashMap<>();

        // This if statement moves the cursor that's within the result set
        if(myResultSet.next()) {
            returnMap = populateCarMap(myResultSet);
        }

        return returnMap;
    }

    /**
     * A helper method to populate a map with a car's information. It is used in another class, hence being public.
     * @param myResultSet of the specified query
     * @return A HashMap representing the car's information
     * @throws SQLException If there was a database access error
     */
    public HashMap<String, Object> populateCarMap(ResultSet myResultSet) throws SQLException {
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("ID", myResultSet.getString("id_car"));
        returnMap.put("Car", myResultSet.getString("name"));
        returnMap.put("Cost", myResultSet.getString("cost"));
        returnMap.put("Mileage", myResultSet.getString("mileage"));
        returnMap.put("Model Year", myResultSet.getString("model_year"));
        returnMap.put("Dealership", myResultSet.getString("dealership"));
        returnMap.put("Car Type", myResultSet.getString("car_type"));
        returnMap.put("Photo", myResultSet.getString("photo"));

        return returnMap;
    }

}
