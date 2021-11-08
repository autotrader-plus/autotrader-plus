package com.example.informationmanipulation;

import com.example.backendlogic.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

class AddUserTest {

    @Test
    @DisplayName("addUser: Basic Case with Simple User")
    void addUserSimple() throws SQLException {
        User user = new User(720, 1000, 5000, "M4Y111", "Mike");

        AddUser.addUser(user);

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "9");
        testMap.put("Name", "Mike");
        testMap.put("Credit Score", "730");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        // TODO: Determine if we're expecting null or strings
        testMap.put("Monthly Income", null);
        testMap.put("Employment Status", null);
        testMap.put("Homeowner", null);
        testMap.put("Monthly Debt Obligation", null);

        System.out.println(testMap);
        System.out.println(ReturnUserInformation.returnUser(9));

        assert testMap.equals(ReturnUserInformation.returnUser(9));
    }

    @Test
    @DisplayName("addUser: Basic Case with Detailed User")
    void addUserDetailed() throws SQLException {
        User user = new User(730, 1000, 5000, "M4Y111", "Mike",
                8500, true, true, 500);

        AddUser.addUser(user);

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "2");
        testMap.put("Name", "Mike");
        testMap.put("Credit Score", "730");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        testMap.put("Monthly Income", "8500");
        testMap.put("Employment Status", "true");
        testMap.put("Homeowner", "true");
        testMap.put("Monthly Debt Obligation", "500");

        assert testMap.equals(ReturnUserInformation.returnUser(2));
    }

}