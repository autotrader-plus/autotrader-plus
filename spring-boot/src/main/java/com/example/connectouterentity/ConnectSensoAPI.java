package com.example.connectouterentity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;


/** This is a class that calls Senso API, and return information from Senso API.**/
public class ConnectSensoAPI {

    private static String content;
    private static int loanAmount;
    private static int creditScore;
    private static int pytBudget;
    private static String vehicleMake;
    private static String vehicleModel;
    private static int vehicleYear;
    private static int vehicleKms;

    public ConnectSensoAPI(HashMap<String, String> senso_input) throws IOException, InterruptedException {
        loanAmount = Integer.parseInt(senso_input.get("loan_amount"));
        creditScore = Integer.parseInt(senso_input.get("credit_score"));
        pytBudget = Integer.parseInt(senso_input.get("payment_budget"));
        vehicleMake = senso_input.get("vehicle_make");
        vehicleModel = senso_input.get("vehicle_model");
        vehicleYear = Integer.parseInt(senso_input.get("vehicle_year"));
        vehicleKms = Integer.parseInt(senso_input.get("vehicle_kms"));

        String input_body = createJson();
        content = CallSensoAPI(input_body);
    }

    private String createJson(){
        return String.format("{\n" +
                "  \"loanAmount\": %s,\n" +
                "  \"creditScore\": %s,\n" +
                "  \"pytBudget\": %s,\n" +
                "  \"vehicleMake\": \"%s\",\n" +
                "  \"vehicleModel\": \"%s\",\n" +
                "  \"vehicleYear\": %s,\n" +
                "  \"vehicleKms\": %s\n" +
                "}", loanAmount, creditScore, pytBudget, vehicleMake, vehicleModel, vehicleYear, vehicleKms);
    }

    private String CallSensoAPI(String inputJson) throws IOException, InterruptedException{

        String postEndpoint = "https://auto-loan-api.senso.ai/rate";

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

        return response.body();
    }

    public String getReturnInfo() {
        return content;
    }
}
