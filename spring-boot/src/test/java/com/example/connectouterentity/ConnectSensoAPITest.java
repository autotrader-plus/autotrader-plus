package com.example.connectouterentity;

import java.io.IOException;
import java.util.HashMap;

class ConnectSensoAPITest {

    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", "10000");
        mapping.put("credit_score", "780");
        mapping.put("payment_budget", "800");
        mapping.put("vehicle_make", "Honda");
        mapping.put("vehicle_model", "Civic");
        mapping.put("vehicle_year", "2021");
        mapping.put("vehicle_kms", "1000");

        ConnectSensoAPI connector = new ConnectSensoAPI(mapping);
        assert connector.getReturnInfo() != null;
        System.out.print(connector.getReturnInfo());

    }


}