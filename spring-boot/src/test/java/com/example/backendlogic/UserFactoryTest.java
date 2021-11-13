package com.example.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

class UserFactoryTest {

    HashMap<String, String> user;
    HashMap<String, String> user2;

    @BeforeEach
    void setUp(){
        HashMap<String, String> user_info = new HashMap<>();
        user_info.put("credit-score", "770");
        user_info.put("monthlybudget", "1000");
        user_info.put("downpayment", "1000");
        user_info.put("zip-code", "M5S 1S5");
        user_info.put("name", "Paul");

        user = user_info;

        HashMap<String, String> user_info2 = new HashMap<>();
        user_info2.put("credit-score", "770");
        user_info2.put("monthlybudget", "1000");
        user_info2.put("downpayment", "1000");
        user_info2.put("zip-code", "M5S 1S5");
        user_info2.put("name", "Paul");
        user_info2.put("monthlyincome", "1300");
        user_info2.put("employed", "employed");
        user_info2.put("homeowner", "homeowner");
        user_info2.put("monthlydebt", "500");

        user2 = user_info2;
    }

    @Test
    void createUser() {
        UserFactory factory = new UserFactory();
        User user_object = factory.createUser(user);
        assert(Objects.equals(user_object.getCreditscore(), "770"));
    }

    @Test
    void createUser2() {
        UserFactory factory = new UserFactory();
        User user_object = factory.createUser(user2);
        assert(Objects.equals(user_object.getMonthlydebt(), "500"));
        assert(Objects.equals(user_object.isEmployed(), "Employed"));
    }
}