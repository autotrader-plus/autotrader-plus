package com.example.returninfo;

import com.example.backendlogic.Loans;
import com.example.informationmanipulation.ReturnMultipleCars;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class ServerMainEndpointHandlerTest {


    @Test
    void httpResponseSenso() {
        // This test is for testing whether the server can be called and can process data as intended.
        // Test replaced with manual testing using Postman, but other associated methods are tested below.
    }

    @Test
    void createLoanResponse() {
        HashMap<String, Object> loans = new HashMap<>();
        ArrayList<String> installments = new ArrayList<>();
        installments.add("{capital=268.8, interest=18.67, installment=287.47, remain=9731.2, interestSum=18.67}");
        loans.put("car1", installments);

        System.out.print(ServerMainEndpointHandler.createLoanResponse(loans));
        assert(Objects.equals(ServerMainEndpointHandler.createLoanResponse(loans),
                "{\"car1\":[\"{capital\\u003d268.8, interest\\u003d18.67, installment\\u003d287.47, remain\\u003d9731.2, interestSum\\u003d18.67}\"]}"));

    }

    @Test
    void parseRequestBody() throws JsonProcessingException {
        HashMap<String, String> info = new HashMap<>();
        info.put("credit_score", "3000");
        info.put("car_preference", "SUV");
        info.put("name", "Paul");
        System.out.print(ServerMainEndpointHandler.parseRequestBody("{\"name\": \"Paul\", \"car_preference\": \"SUV\", \"credit_score\": \"3000\"}"));
        assert(Objects.equals(ServerMainEndpointHandler.parseRequestBody("{\"name\": \"Paul\", \"car_preference\": \"SUV\", \"credit_score\": \"3000\"}"), info));
    }

    @Test
    void getFilteredCars() throws SQLException {
        assert(ServerMainEndpointHandler.getFilteredCars("SUV").get(0).get("Model Year").equals("2017"));
        assert(ServerMainEndpointHandler.getFilteredCars("SUV").get(0).get("Car").equals("Honda Pillot 4WD"));
    }

    @Test
    void getAllCars(){
        // this is an analagous test for ReturnMultipleCars.returnAllCars();
        // See ReturnMultipleCarsTest for the test.
    }
}