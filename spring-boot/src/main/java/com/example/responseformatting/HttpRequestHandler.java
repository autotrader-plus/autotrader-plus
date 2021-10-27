package com.example.responseformatting;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**This is dummy class that demonstrates how to get request body from a http request into the server.**/
/**TODO: Delete this class if it is not needed anymore **/
@RestController
public class HttpRequestHandler {

    @PostMapping("/postbody")
    public static String handle(@RequestBody String postbody){
        return "works! " + postbody;

    }

}