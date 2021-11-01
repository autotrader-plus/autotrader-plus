package com.example.returninfo;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ServerMainEndpointHandlerTest {
    public static void main(String[] args) throws JsonProcessingException {
        testCreateLoanResponse();
        testParseRequestBody();
    }

    private static void testCreateLoanResponse(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        HashMap<String, String> item2 = new HashMap<>();
        item1.put("1", "this is item number 1");
        item1.put("1-info", "this is info for item number 1");
        item2.put("2", "this is item number 2");
        item2.put("2-info", "this is info for item number 2");
        list.add(item1);
        list.add(item2);

        System.out.println(ServerMainEndpointHandler.createLoanResponse(list));

    }

    private static void testParseRequestBody() throws JsonProcessingException {
        String json = "{\"car-preference\": \"SUV\", \"Downpayment\": \"3000\", \"credit-score\": \"780\", \"name\": \"Paul\", \"zip code\": \"M5S 1S5\", \"monthlybudget\": \"500\"}";
        HashMap<String, String> returnHashmap = new HashMap<>();
        returnHashmap.put("car-preference", "SUV");
        returnHashmap.put("Downpayment", "3000");
        returnHashmap.put("credit-score", "780");
        returnHashmap.put("name", "3000");
        returnHashmap.put("zip code", "M5S 1S5");
        returnHashmap.put("monthlybudget", "500");

        System.out.println(ServerMainEndpointHandler.parseRequestBody(json));
    }

}