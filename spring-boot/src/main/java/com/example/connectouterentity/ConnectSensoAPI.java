package com.example.connectouterentity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/** This is a class that calls Senso API, and return information from Senso API.**/
public class ConnectSensoAPI {

    private final String content;

    public ConnectSensoAPI() throws IOException, InterruptedException{

        String postEndpoint = "https://auto-loan-api.senso.ai/rate";

        /**TODO: Create a method that takes in user and car information, instead of harcoding values.**/
        String inputJson = "{\n" +
                "  \"loanAmount\": 10000,\n" +
                "  \"creditScore\": 780,\n" +
                "  \"pytBudget\": 800,\n" +
                "  \"vehicleMake\": \"Honda\",\n" +
                "  \"vehicleModel\": \"Civic\",\n" +
                "  \"vehicleYear\": 2021,\n" +
                "  \"vehicleKms\": 1000\n" +
                "}";

        // Build and send a POST request to senso endpoint
        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndpoint))
                .header("Content-Type", "application/json")
                .header("x-api-key", "AIzaSyCD_-qCdXqrvWGHN1tpe2PH6Rf8zpnTdXs")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        var client = HttpClient.newHttpClient();

        // Get response from senso api
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        this.content = response.body();
    }

    /**TODO: Create a method that parse responses from Senso API.**/

    public String getReturnInfo() {
        return this.content;
    }
}
