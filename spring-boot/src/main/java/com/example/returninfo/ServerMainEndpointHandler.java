package com.example.returninfo;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseformatting.*;
import com.example.connectouterentity.*;

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
     * @throws IOException
     * @throws InterruptedException
     */
    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @PostMapping("/traderauto-plus")
    public HttpResponseMain httpResponseSenso(@RequestParam(required = true) String req_body) throws IOException, InterruptedException{
        System.out.println("==== POST Request Received ====");
//        JSONObject body = parseRequestBody(req_body);
//        JSONObject return_body = getReturnInfo(body);

//        HttpResponseMain response = new HttpResponseMain(counter, return_body);
        System.out.println("==== POST Response Sent ====");
//        return response;
        return null;
    }

//    private JSONObject getReturnInfo(JSONObject body) {
//        //TODO: Complete this method.
//    }
//
//    private JSONObject parseRequestBody(String req_body) {
//        //TODO: Complete this method.
//    }

}
