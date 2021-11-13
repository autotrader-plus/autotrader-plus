package com.example.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarListTest {

    CarList<Car> testCarList;
    Car c1;

    @BeforeEach
    void setUp() {
        testCarList = new CarList<>();

        c1 = new Car("2016", "Infiniti QX50", "70953", "Wagon",
                "24", "25900", "Dixie Infiniti");
    }

    @Test
    void AddToList() {
        testCarList.AddtoList(c1);
        assert testCarList.getCar(0).equals(c1);
    }

    @Test
    void size() {
        assert testCarList.size() == 0;
    }

    @Test
    void getCar() {
        testCarList.AddtoList(c1);
        assert testCarList.getCar(0).equals(c1);
    }
}