package com.example.informationmanipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ReturnMultipleCarsTest {


    @Test
    @DisplayName("AllCars: Basic Case")
    void returnAllCars() throws SQLException {

        ArrayList<HashMap<String, String>> testList = new ArrayList<>();

        // TODO: Populate testList with all cars in the database

        assert Objects.equals(testList, ReturnMultipleCars.returnAllCars());
    }

    @Test
    @DisplayName("FilteredCars: Basic Case")
    void returnFilteredCars() throws SQLException {

        String filter = "SUV";

        ArrayList<HashMap<String, String>> testList = new ArrayList<>();

        // TODO: Populate testList with all cars that are SUVs from the database


        assert Objects.equals(testList, ReturnMultipleCars.returnFilteredCars(filter));
    }

}