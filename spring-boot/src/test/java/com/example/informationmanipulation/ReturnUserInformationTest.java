package com.example.informationmanipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ReturnUserInformationTest {

    @Test
    @DisplayName("User: Basic Case with Simple User")
    void returnUserSimple() throws SQLException {
        // TODO: Actually run with refactored code to make sure it works (run along with AddUserTest, simple case)
        Integer user_id = 12;

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "12");
        testMap.put("Name", "Mike");
        testMap.put("Credit Score", "740");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        testMap.put("Monthly Income", "0");
        testMap.put("Employment Status", "false");
        testMap.put("Homeowner", "false");
        testMap.put("Monthly Debt Obligation", "0");

        assert Objects.equals(testMap, ReturnUserInformation.returnUser(user_id));
    }

    @Test
    @DisplayName("User: Basic Case with Detailed User")
    void returnUserDetailed() throws SQLException {
        Integer user_id = 1;

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "1");
        testMap.put("Name", "Ameen");
        testMap.put("Credit Score", "750");
        testMap.put("Location", "M5V0P5");
        testMap.put("Max Downpayment", "150");
        testMap.put("Max Monthly Payment", "2000");
        testMap.put("Monthly Income", "1000");
        testMap.put("Employment Status", "true");
        testMap.put("Homeowner", "false");
        testMap.put("Monthly Debt Obligation", "1000");

        assert Objects.equals(testMap, ReturnUserInformation.returnUser(user_id));
    }

}