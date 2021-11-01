package com.example.returninfo;

import com.example.backendlogic.Loans;
import com.example.informationmanipulation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public HttpResponseMain httpResponseSenso(@RequestBody() String req_body) throws IOException, SQLException, InterruptedException {
        System.out.println("==== POST Request Received ====");
        HashMap<String, String> body = parseRequestBody(req_body);

        ArrayList<HashMap<String, String>> filtered_cars = getFilteredCars(body.get("car-preference"));
        body.remove("car-preference");
        Loans loans = new Loans(body, filtered_cars);
        ArrayList<String> loans_list = loans.getLoans();
        String response = createLoanResponse(loans_list);

        HttpResponseMain http_response = new HttpResponseMain(counter.incrementAndGet(), response);
        System.out.println("==== POST Response Sent ====");
        return http_response;

    }

    // Create a String representation of the loan information for the first month to be sent back as the http response
    public static String createLoanResponse(ArrayList<String> loans) {
        StringBuilder loan_info = new StringBuilder();

        loan_info.append("{");

        for (String loan : loans) {
            loan_info.append("{");
            loan_info.append(loan);
            loan_info.append("}");
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
    private ArrayList<HashMap<String, String>> getFilteredCars(String car_type) throws SQLException {
        return ReturnMultipleCars.returnFilteredCars(car_type);
    }
}
