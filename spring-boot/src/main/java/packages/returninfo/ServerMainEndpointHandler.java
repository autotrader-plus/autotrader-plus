package packages.returninfo;

import packages.backendlogic.LoanInfoInterface;
import packages.backendlogic.Loans;
import packages.exceptions.DatabaseConnectionFailureException;
import packages.exceptions.SensoConnectionFailureException;
import packages.informationmanipulation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import packages.responseformatting.HttpResponseMain;

/** This class handles HTTP request to the "/traderauto-plus" endpoint. **/
@RestController
@CrossOrigin(origins ="*")
public class ServerMainEndpointHandler {

    /**
     * This method handles HTTP request coming into the "/traderauto-plus" endpoint. It processes information from the request
     * body and returns information back to the client.
     *
     * @param req_body - the body of the http request from the client
     * @return A HTTP Response back to the client.
     */
    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @PostMapping("/traderauto-plus")
    public String httpResponseSenso(@RequestBody() String req_body) {
        System.out.println("==== POST Request Received ====");
        try {
            HashMap<String, String> body = parseRequestBody(req_body);

            //get car list based on user's preference, otherwise get all cars from database
            ArrayList<HashMap<String, Object>> filtered_cars;
            if (!Objects.equals(body.get("car-preference"), "")) {
                filtered_cars = getFilteredCars(body.get("car-preference"));
            } else {
                filtered_cars = getAllCars();
            }


            body.remove("car-preference");

            // calculate loans for all cars that are filtered and create a response to send back to the client
            LoanInfoInterface loans = new Loans(body, filtered_cars);
            HashMap<String, Object> loans_list = loans.calculateLoans(body, filtered_cars);

            HttpResponseMain http_response = new HttpResponseMain(loans_list);
            System.out.println("==== POST Response Sent ====");
            return http_response.getContent();
        } catch (SensoConnectionFailureException|JsonProcessingException|DatabaseConnectionFailureException e){
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        // loans information is not returned due to senso api connection failure, returns error message instead
        return "Unable to retrieve loan information. Please try again!";
    }

    /**
     * Parse the request body
     * @param req_body - a string representation of the request json
     * @return a hashmap representation of the request json
     * @throws JsonProcessingException - error thrown when json cannot be processed
     */
    private HashMap<String, String> parseRequestBody(String req_body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> userInfoHash = objectMapper.readValue(req_body, HashMap.class);

        return userInfoHash;
    }


    /**
     * Get filtered car list based on car_type specified in the http request body
     * @param car_type - the car_type to return from the database
     * @return an array list of cars that fit the car_type
     * @throws DatabaseConnectionFailureException If failed to connect to the database
     */
    ArrayList<HashMap<String, Object>> getFilteredCars(String car_type) throws DatabaseConnectionFailureException {
        try {
            ReturnMultipleCars returnMultipleCars = new ReturnMultipleCars();
            return returnMultipleCars.returnFilteredCars(car_type);
        } catch(SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionFailureException();
        }
    }

    /**
     * A helper methods to return all cars in the database
     * @return an array list containg the information for all cars
     * @throws DatabaseConnectionFailureException If failed to connect to the database
     */
    private ArrayList<HashMap<String, Object>> getAllCars() throws DatabaseConnectionFailureException {
        try {
            ReturnMultipleCars r = new ReturnMultipleCars();
            return r.returnAllCars();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionFailureException();
        }
    }
}
