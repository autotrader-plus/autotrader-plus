package com.example.informationmanipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ReturnCarInformationTest {

    @Test
    @DisplayName("CarDetailString: Basic Case")
    void returnCarDetailsString() throws SQLException {
        assert Objects.equals(ReturnCarInformation.returnCarDetailsString(1), "ID: 1\nCar: Hyundai Sonata Ultimate\n" +
                "Cost: $35649\nMileage: 10323\nModel Year: 2021\nDealership: Guelph Hyundai\nCar Type: Convertible");
    }

    @Test
    @DisplayName("CarDetails: Basic Case")
    void returnCarDetails() throws SQLException {
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "1");
        testMap.put("Car", "Hyundai Sonata Ultimate");
        testMap.put("Cost", "35649");
        testMap.put("Mileage", "10323");
        testMap.put("Model Year", "2021");
        testMap.put("Dealership", "Guelph Hyundai");
        testMap.put("Car Type", "Convertible");
        assert Objects.equals(testMap, ReturnCarInformation.returnCarDetails(1));
    }

}