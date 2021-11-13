package com.example.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarListTest {

    @BeforeEach
    void setUp() {
        CarList<Car> testCarList = new CarList<>();

        Car c1 = new Car("2016", "Infiniti QX50", "70953", "Wagon",
                "24", "25900", "Dixie Infiniti");
    }

    @Test
    void AddToList(CarList<Car> testCarList, Car c1) {
        testCarList.AddtoList(c1);
        assert testCarList.getCar(1).equals(c1);
    }

    @Test
    void size(CarList<Car> testCarList) {
        assert testCarList.size() == 1;
    }

    @Test
    void getCar(CarList<Car> testCarList, Car c1) {
        assert testCarList.getCar(1).equals(c1);
    }
}