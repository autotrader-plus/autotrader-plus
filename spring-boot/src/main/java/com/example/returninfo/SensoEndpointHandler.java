package com.example.returninfo;

import java.io.IOException;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseformatting.*;
import com.example.connectouterentity.*;

/** This class handles HTTP request to the "/senso" endpoint. This class may be retired and deleted since we
 * might not need to use this endpoint for the real project. For now, this class will remain here in case we decide that
 * we need to call this endpoint in the future.**/
@RestController
@CrossOrigin(origins ="*")
public class SensoEndpointHandler {

    private final AtomicLong counter = new AtomicLong();

    /**This method calls and connects the Senso API, and returns the information from Senso API
     *
     * @return The String content from SensoAPI call
     * @throws IOException
     * @throws InterruptedException
     */
    private String getSensoReturnInfo(HashMap<String, String> senso_input) throws IOException, InterruptedException {

        ConnectSensoAPI senso_object = new ConnectSensoAPI(senso_input);
        return senso_object.getReturnInfo();

    }
    /**
     * then further process the information before returning a response
     *
     * This method handles HTTP request coming into the "/senso" endpoint. It processes information from the request
     * body and returns information back to the client.
     *
     * @param req_body - the body of the http request from the client
     * @return A HTTP Response back to the client.
     * @throws IOException
     * @throws InterruptedException
     */
    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @GetMapping("/senso")
    public HttpResponseSenso httpResponseSenso(@RequestParam(required = false, defaultValue = "") String req_body) throws IOException, InterruptedException{
        System.out.println("==== Connecting to Senso API ====");
        HashMap<String, String> request = toMapping(req_body);
        return new HttpResponseSenso(counter.incrementAndGet(), getSensoReturnInfo(request));
    }

    private HashMap<String, String> toMapping(String req_body){
        System.out.println(req_body);
        return new HashMap<>();
    }

}
