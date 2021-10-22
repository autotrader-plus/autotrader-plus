package com.example.springboot;

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

@RestController
public class ReturnSensoAPI {

    private final AtomicLong counter = new AtomicLong();

    public String getContent() throws IOException, InterruptedException {
        ConnectSensoAPI senso_object = new ConnectSensoAPI();
        return senso_object.getContent();

    }

    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @GetMapping("/senso")
    public HttpResponseSenso httpResponseSenso(@RequestParam(required = false, defaultValue = "") String name) throws IOException, InterruptedException{
        System.out.println("==== connect and respond to senso api ====");
        HttpResponseSenso senso_response = new HttpResponseSenso(counter.incrementAndGet(), getContent());
        return senso_response;
    }
}