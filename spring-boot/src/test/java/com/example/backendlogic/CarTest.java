package com.example.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @BeforeEach
    void setUp() {
        Car c1 = new Car("2016", "Infiniti QX50", "71935", "Wagon",
                "24", "25895", "Dixie Infiniti");
    }

// Will implement test when method is used in the future
//    @Test
//    void CanAfford() {
//    }

    @Test
    void getPrice(Car c1) {
        assert c1.getPrice() == 25895;
    }

    @Test
    void getBrand(Car c1) {
        assert c1.getBrand().equals("Infiniti QX50");
    }

    @Test
    void getYear(Car c1) {
        assert c1.getYear().equals("2016");
    }

    @Test
    void getKMS(Car c1) {
        assert c1.getKMS().equals("71935");
    }

    @Test
    void returnID(Car c1) {
        assert c1.returnID().equals("24");
    }

// Will implement test when method is used in the future
//    @Test
//    void show() {
//    }
}