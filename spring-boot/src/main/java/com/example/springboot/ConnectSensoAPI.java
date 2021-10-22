package com.example.springboot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectSensoAPI {

    @GetMapping("/senso")
    public static String main(String[] args) throws IOException, InterruptedException{
        String postEndpoint = "https://auto-loan-api.senso.ai/rate";

        String inputJson = "{\n" +
                "  \"loanAmount\": 10000,\n" +
                "  \"creditScore\": 780,\n" +
                "  \"pytBudget\": 800,\n" +
                "  \"vehicleMake\": \"Honda\",\n" +
                "  \"vehicleModel\": \"Civic\",\n" +
                "  \"vehicleYear\": 2021,\n" +
                "  \"vehicleKms\": 1000\n" +
                "}";

        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndpoint))
                .header("Content-Type", "application/json")
                .header("x-api-key", "AIzaSyCD_-qCdXqrvWGHN1tpe2PH6Rf8zpnTdXs")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response.statusCode());
//        System.out.println(response.body());
        return response.body();
    }
}