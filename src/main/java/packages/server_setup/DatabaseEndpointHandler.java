package packages.server_setup;

import java.sql.*;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import packages.exceptions.DatabaseConnectionFailureException;
import packages.database_info_manipulation.*;
import packages.http_parser_constructor.HttpResponseConstructor;
import packages.http_parser_constructor.HttpRequestParser;

@RestController
@CrossOrigin(origins ="*")
public class DatabaseEndpointHandler {

    /**
     * This will return the information queried by the DatabaseEndpointHandler class
     * @return a hashmap for the car inforamtion
     */
    public HashMap<String, Object> getContent(int carId) throws DatabaseConnectionFailureException {

        try {
            ReturnCarInformation dbObject = new ReturnCarInformation();
            return dbObject.returnCarDetails(carId);
        } catch(SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionFailureException();
        }
    }

    /**
     * This method handles all POST request to the "/database" endpoint. This endpoints deal with
     * returning information about a car.
     * @param carId the request body needs to contain the carID
     * @return a JSON string representation of the information regarding the car with carID
     */
    @CrossOrigin
    @PostMapping("/database")
    public String httpResponseMain(@RequestBody String reqBody) {
        try {
            // parse request body
            HttpRequestParser parser = new HttpRequestParser();
            HashMap<String, String> body = parser.parseRequestBody(reqBody);
            System.out.println("==== Connecting to Autotrader Database ====");

            // get car information
            String carID = body.get("key");
            HashMap<String, Object> response = getContent(Integer.parseInt(carID));

            // construct http response
            HttpResponseConstructor http_response = new HttpResponseConstructor(response);
            System.out.printf("==== Returning Car Information for Car %s ====%n", carID);
            return http_response.getContent();
        } catch(DatabaseConnectionFailureException|JsonProcessingException e){
            e.printStackTrace();
            System.err.println("Error:" + e.getMessage());
        }

        // loans information is not returned due to database connection failure, returns error message instead
        return "Unable to retrieve database information. Please try again!";
    }

}