package com.example.returninfo;

import com.example.backendlogic.Loans;
import com.example.informationmanipulation.ReturnMultipleCars;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

class ServerMainEndpointHandlerTest {
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
//        testCreateLoanResponse();
        testParseRequestBody();
        testCreateLoanResponse2();
    }

//    private static void testCreateLoanResponse(){
//        ArrayList<HashMap<String, String>> list = new ArrayList<>();
//        HashMap<String, String> item1 = new HashMap<>();
//        HashMap<String, String> item2 = new HashMap<>();
//        item1.put("1", "this is item number 1");
//        item1.put("1-info", "this is info for item number 1");
//        item2.put("2", "this is item number 2");
//        item2.put("2-info", "this is info for item number 2");
//        list.add(item1);
//        list.add(item2);
//
//        System.out.println(ServerMainEndpointHandler.createLoanResponse(list));
//
//    }

    private static void testCreateLoanResponse2() throws IOException, InterruptedException, SQLException {
        ArrayList<HashMap<String, String>> carlist = ReturnMultipleCars.returnAllCars();
        HashMap<String, String> userinfo = new HashMap<>();
        userinfo.put("credit-score", "770");
        userinfo.put("monthlybudget", "600");
        userinfo.put("downpayment", "200");
        userinfo.put("zip-code", "M4y111");
        userinfo.put("name", "Bob");
        Loans loan = new Loans(userinfo, carlist);
        ArrayList output = loan.getLoans();

        System.out.print(ServerMainEndpointHandler.createLoanResponse(output));

    }

    private static void testParseRequestBody() throws JsonProcessingException {
        String json = "{\"car-preference\": \"SUV\", \"Downpayment\": \"3000\", \"credit-score\": \"780\", \"name\": \"Paul\", \"zip code\": \"M5S 1S5\", \"monthlybudget\": \"500\"}";
        HashMap<String, String> returnHashmap = new HashMap<>();
        returnHashmap.put("car-preference", "SUV");
        returnHashmap.put("downpayment", "3000");
        returnHashmap.put("credit-score", "780");
        returnHashmap.put("name", "3000");
        returnHashmap.put("zip-code", "M5S 1S5");
        returnHashmap.put("monthlybudget", "500");

        System.out.println(ServerMainEndpointHandler.parseRequestBody(json));
    }

}