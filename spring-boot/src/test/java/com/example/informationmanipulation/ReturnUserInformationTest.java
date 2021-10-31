package com.example.informationmanipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ReturnUserInformationTest {


    @Test
    @DisplayName("User: Basic Case")
    void returnUser() throws SQLException {

        Integer user_id = 1;

        HashMap<String, String> testMap = new HashMap<>();

        // TODO: Populate testMap with a user that exists in the database
        
        testMap.put("ID", "");
        testMap.put("Name", "");
        testMap.put("Credit Score", "");
        testMap.put("Location", "");
        testMap.put("Max Downpayment", "");
        testMap.put("Max Monthly Payment", "");
        testMap.put("Monthly Income", "");
        testMap.put("Employment Status", "");
        testMap.put("Homeowner", "");
        testMap.put("Monthly Debt Obligation", "");

        assert Objects.equals(testMap, ReturnUserInformation.returnUser(user_id));
    }

}