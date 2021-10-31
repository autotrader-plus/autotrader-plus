package com.example.returninfo;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ServerMainEndpointHandlerTest {
    public static void main(String[] args) {
        testCreateLoanResponse();
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

        System.out.print(ServerMainEndpointHandler.createLoanResponse(list));

    }

}