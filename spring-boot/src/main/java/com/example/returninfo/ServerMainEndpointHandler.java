package com.example.returninfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
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
    public HttpResponseMain httpResponseSenso(@RequestParam() String req_body){
        System.out.println("==== POST Request Received ====");
        JSONObject body = parseRequestBody(req_body);

        ArrayList<HashMap<String, String>> filtered_cars = getFilteredCars((String) body.get("car_preference"));
        ArrayList<> loan = DummyMethod(filtered_cars); //TODO: Call Daniel's loan class


        String response = createResponse(loan);
        HttpResponseMain response = new HttpResponseMain(counter, response);
        System.out.println("==== POST Response Sent ====");
        return response;

    }

    private String createResponse(ArrayList loan) {
        //TODO: Complete this method based on return type of Daniel's loan class
    }

    private JSONObject getReturnInfo(JSONObject body) {
        //TODO: Complete this method (Need to call Loan class method here).
    }

    private JSONObject parseRequestBody(String req_body) {
        //TODO: Complete this method (Need to call a method from parse class).
    }

    private ArrayList<HashMap<String, String>> getFilteredCars(String car_type){
        // TODO: Call Ameen's returnFilteredCars(car_type)
    }

}
