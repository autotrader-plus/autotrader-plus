package packages.returninfo;

import packages.backendlogic.AuthenticateUser;

import java.sql.SQLException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** This class handles HTTP request to the "/signin" endpoint. **/
@RestController
@CrossOrigin(origins ="*")
public class SignInEndpointHandler {

    /**
     * This method handles HTTP request coming into the "/signin" endpoint. It processes information from the request
     * body and returns information back to the client.
     *
     * @param req_body - the body of the http request from the client
     * @return An HTTP Response back to the client.
     */
    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @PostMapping("/signin")
    public String httpResponseSenso(@RequestBody() String req_body) {
        System.out.println("==== POST Request Received ====");
        try {
            // format of
            // {"username": username, "password": password}
            HashMap<String, String> body = parseRequestBody(req_body);

            // Create AuthenticateUser object
            AuthenticateUser auth = new AuthenticateUser();
            if (auth.checkUser( body.get("username"), body.get("password") )){
                // Valid set of credentials
                System.out.println("==== POST Response Sent ====");
                return "true";
            }
            else{
                // Invalid credentials
                System.out.println("==== POST Response Sent ====");
                return "";
            }

        } catch (JsonProcessingException | SQLException e){
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        // JSON processing or SQL error occurred, print this error message
        return "Unable to process JSON or SQL error, please try again!";
    }

    /**
     * Parse the request body
     * @param req_body - a string representation of the request json
     * @return a hashmap representation of the request json
     * @throws JsonProcessingException - error thrown when json cannot be processed
     */
    static HashMap<String, String> parseRequestBody(String req_body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> userInfoHash = objectMapper.readValue(req_body, HashMap.class);

        return userInfoHash;
    }

}
