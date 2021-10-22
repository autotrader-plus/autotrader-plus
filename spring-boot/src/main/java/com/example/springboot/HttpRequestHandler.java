package com.example.springboot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpRequestHandler {

    @GetMapping("/response")


    @PostMapping("/postbody")
    public void handle(@RequestBody String postbody){
        return "works!\n" + postbody;

    }

}