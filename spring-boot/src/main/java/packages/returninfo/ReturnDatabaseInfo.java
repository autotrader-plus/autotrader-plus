package packages.returninfo;

import java.sql.*;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import packages.exceptions.DatabaseConnectionFailureException;
import packages.informationmanipulation.*;
import packages.responseformatting.HttpResponseMain;
import packages.responseformatting.HttpRequestParser;

@RestController
@CrossOrigin(origins ="*")
public class ReturnDatabaseInfo {

    /**
     * This will return the information queried by the ReturnDatabaseInfo class
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
     * This method handles all POST request to the "/database" endpoint.
     * @param carId the request body needs to contain the carID
     * @return a JSON string representation of the information regarding the car with carID
     */
    @CrossOrigin(origins = "http://ec2-18-118-19-97.us-east-2.compute.amazonaws.com:8080")
    @PostMapping("/database")
    public String httpResponseMain(@RequestBody String reqBody) {
        try {
            HttpRequestParser parser = new HttpRequestParser();
            HashMap<String, String> body = parser.parseRequestBody(reqBody);
            System.out.println("==== Connecting to Autotrader Database ====");

            String carId = body.get("key");

            HashMap<String, Object> response = getContent(Integer.parseInt(carId));
            HttpResponseMain http_response = new HttpResponseMain(response);
            System.out.printf("==== Returning Car Information for Car %s ====%n", carId);
            return http_response.getContent();
        } catch(DatabaseConnectionFailureException|JsonProcessingException e){
            e.printStackTrace();
            System.err.println("Error:" + e.getMessage());
        }

        // loans information is not returned due to database connection failure, returns error message instead
        return "Unable to retrieve database information. Please try again!";
    }

}