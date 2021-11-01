package com.example.returninfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseformatting.*;

/** This class handles HTTP request to the "/senso" endpoint. **/
@RestController
@CrossOrigin(origins ="*")
public class ServerMainEndpointHandler {

    private final AtomicLong counter = new AtomicLong();

    /**
     * This method handles HTTP request coming into the "/traderauto-plus" endpoint. It processes information from the request
     * body and returns information back to the client.
     *
     * @param req_body - the body of the http request from the client
     * @return A HTTP Response back to the client.
     */
    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @PostMapping("/traderauto-plus")
    public HttpResponseMain httpResponseSenso(@RequestParam() String req_body) throws JsonProcessingException {
        System.out.println("==== POST Request Received ====");
        HashMap<String, String> body = parseRequestBody(req_body);

        ArrayList<HashMap<String, String>> filtered_cars = getFilteredCars((String) body.get("car-preference"));
        ArrayList<HashMap<String, String>> loans = DummyMethod(filtered_cars); //TODO: Call Daniel's loan class

        String response = createLoanResponse(loans);

        HttpResponseMain http_response = new HttpResponseMain(counter, response);
        System.out.println("==== POST Response Sent ====");
        return http_response;

    }

    // Create a String representation of all the loans information to be sent back as the http response
    static String createLoanResponse(ArrayList<HashMap<String, String>> loans) {
        StringBuilder loan_info = new StringBuilder();

        loan_info.append("{");

        for (HashMap<String, String> loan : loans) {
            loan_info.append("{");
            for (String key : loan.keySet()){

                loan_info.append("\"").append(key).append("\"").append(": ");
                loan_info.append("\"").append(loan.get(key)).append("\"");
                loan_info.append(", ");
            }
            loan_info.delete(loan_info.length()-2, loan_info.length());
            loan_info.append("}");
            loan_info.append(",\n");
        }

        loan_info.append("}");

        return loan_info.toString();
    }

    // Parse the request body
    static HashMap<String, String> parseRequestBody(String req_body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> userInfoHash = objectMapper.readValue(req_body, HashMap.class);

        return userInfoHash;
    }

    // get a filtered car list based on http request body
    private ArrayList<HashMap<String, String>> getFilteredCars(String car_type){
        // TODO: Call Ameen's returnFilteredCars(car_type)
    }
}
