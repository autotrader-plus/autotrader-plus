package packages.server_setup;

import packages.backend_logic.LoanInfoInterface;
import packages.backend_logic.LoanResponseCalculator;
import packages.backend_logic.Loans;
import packages.exceptions.DatabaseConnectionFailureException;
import packages.exceptions.SensoConnectionFailureException;
import packages.database_info_manipulation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import packages.http_parser_constructor.HttpResponseConstructor;
import packages.http_parser_constructor.HttpRequestParser;

/** This class handles HTTP request to the "/traderauto-plus" endpoint. **/
@RestController
@CrossOrigin(origins ="*")
public class ServerMainEndpointHandler {

    public final static String SERVER = System.getenv("SERVER_ROOT");

    /**
     * This method handles HTTP request coming into the "/traderauto-plus" endpoint. It processes information from the request
     * body and returns information back to the client. This endpoint assumes that all users that go through here are new users.
     *
     * @param reqBody - the body of the http request from the client
     * @return A HTTP Response back to the client.
     */
    @CrossOrigin
    @PostMapping("/traderauto-plus")
    public String httpResponseSenso(@RequestBody() String reqBody) {
        System.out.println("==== POST Request Received ====");
        try {
            HttpRequestParser parser = new HttpRequestParser();
            HashMap<String, String> body = parser.parseRequestBody(reqBody);

            //get car list based on user's preference, otherwise get all cars from database
            ArrayList<HashMap<String, Object>> filtered_cars;
            if (!Objects.equals(body.get("car-preference"), "")) {
                filtered_cars = getFilteredCars(body.get("car-preference"));
            } else {
                filtered_cars = getAllCars();
            }

            body.remove("car-preference");
            addUserToDatabase(body);

            // calculate loans for all cars that are filtered and create a response to send back to the client
            LoanInfoInterface loans = new LoanResponseCalculator(body, filtered_cars);
            HashMap<String, Object> loans_list = loans.calculateLoans(body, filtered_cars);

            HttpResponseConstructor httpResponse = new HttpResponseConstructor(loans_list);
            System.out.println("==== POST Response Sent ====");
            return httpResponse.getContent();
        } catch (SensoConnectionFailureException|DatabaseConnectionFailureException e){
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        } catch (InterruptedException| IOException e){
            e.printStackTrace();
            System.err.println("Process Interrupted due to server error, please make sure you are entering all information" +
                    "in the correct format.");
        }


        // loans information is not returned due to senso api connection failure, returns error message instead
        return "Unable to retrieve loan information. Please try again!";
    }

    /**
     * Get filtered car list based on carType specified in the http request body
     * @param carType - the carType to return from the database
     * @return an array list of cars that fit the carType
     * @throws DatabaseConnectionFailureException If failed to connect to the database
     */
    private ArrayList<HashMap<String, Object>> getFilteredCars(String carType) throws DatabaseConnectionFailureException {
        try {
            ReturnMultipleCars returnMultipleCars = new ReturnMultipleCars();
            return returnMultipleCars.returnFilteredCars(carType);
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
            ReturnMultipleCars connection = new ReturnMultipleCars();
            return connection.returnAllCars();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionFailureException();
        }
    }

    /**
     * Add user to database
     * @param user - the user hashmap containing user information
     * @throws DatabaseConnectionFailureException error thrown if failure happens when connecting to the database
     */
    private void addUserToDatabase(HashMap<String, String> user) throws DatabaseConnectionFailureException {
        try {
            AddUser connection = new AddUser();
            connection.addUser(user);
        } catch(SQLException e){
            throw new DatabaseConnectionFailureException();
        }
    }
}
