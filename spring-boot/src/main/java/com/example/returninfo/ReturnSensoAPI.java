package com.example.returninfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseformatting.*;
import com.example.connectouterentity.*;

/** This class handles HTTP request to the "/senso" endpoint. **/
@RestController
@CrossOrigin(origins ="*")
public class ReturnSensoAPI {

    private final AtomicLong counter = new AtomicLong();

    /**This method calls and connects the Senso API, and returns the information from Senso API
     *
     * @return The String content from SensoAPI call
     * @throws IOException
     * @throws InterruptedException
     */
    public String getContent() throws IOException, InterruptedException {
        ConnectSensoAPI senso_object = new ConnectSensoAPI();
        return senso_object.getContent();

    }

    /** TODO: Create or modify the method below such that it can read the information in http request body,
     * then further process the information before returning a response
     *
     * This method handles HTTP request coming into the "/senso" endpoint. It processes information from the request
     * body and returns information back to the client.
     *
     * @param name
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @GetMapping("/senso")
    public HttpResponseSenso httpResponseSenso(@RequestParam(required = false, defaultValue = "") String name) throws IOException, InterruptedException{
        System.out.println("==== connect and respond to senso api ====");
        HttpResponseSenso senso_response = new HttpResponseSenso(counter.incrementAndGet(), getContent());
        return senso_response;
    }

    /** TODO: Create a method that parse information from front-end. **/
}