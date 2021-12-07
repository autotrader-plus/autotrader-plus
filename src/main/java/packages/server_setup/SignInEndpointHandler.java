package packages.server_setup;

import packages.database_info_manipulation.AuthenticateUser;

import java.sql.SQLException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import packages.exceptions.DatabaseConnectionFailureException;
import packages.http_parser_constructor.HttpResponseConstructor;
import packages.http_parser_constructor.HttpRequestParser;

/** This class handles HTTP request to the "/signin" endpoint. **/
@RestController
@CrossOrigin(origins ="*")
public class SignInEndpointHandler {

    /**
     * This method handles HTTP request coming into the "/signin" endpoint. It processes information from the request
     * body and returns information back to the client.
     * This endpoint is to verify whether the user information (username and password) matches the
     * information in our database.
     *
     * @param reqBody - the body of the http request from the client
     * @return An HTTP Response back to the client.
     */
    @CrossOrigin
    @PostMapping("/signin")
    public String httpResponseSignIn(@RequestBody() String reqBody) {
        System.out.println("==== POST Request Received ====");
        try {
            // format of {"username": username, "password": password}
            // parse http response
            HttpRequestParser parser = new HttpRequestParser();
            HashMap<String, String> body = parser.parseRequestBody(reqBody);

            // Create AuthenticateUser object and validate username and password
            HashMap<String, Object> resultMapping = getValidationResult(body);

            // construct http response
            HttpResponseConstructor httpResponse = new HttpResponseConstructor(resultMapping);
            System.out.println("==== POST Response Sent ====");
            return httpResponse.getContent();

        } catch (JsonProcessingException | DatabaseConnectionFailureException e){
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        // JSON processing or SQL error occurred, print this error message
        return "Unable to process JSON or failure to connect to database, please try again!";
    }

    /**
     * Validate username and password
     * @param body - a hashmap containing username and password
     * @return a hashmap containing the result of validation
     * @throws SQLException - exception thrown when error
     */
    private HashMap<String, Object> getValidationResult(HashMap<String, String> body) throws DatabaseConnectionFailureException {
        AuthenticateUser authenticator = new AuthenticateUser();
        HashMap<String, Object> resultMapping = new HashMap<>();

        // check whether username and password matches record in database, then return result
        if (authenticator.checkUser( body.get("username"), body.get("password") )){
            // Valid set of credentials
            resultMapping.put("Authentication", "Successful");
        }
        else{
            // Invalid credentials
            resultMapping.put("Authentication", "Unsuccessful");
        }
        return resultMapping;
    }
}
