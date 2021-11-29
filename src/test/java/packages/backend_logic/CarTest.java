package packages.backend_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car("2016", "Infiniti QX50", "71935", "Wagon",
                "24", "25895", "Dixie Infiniti");
    }

// Will implement test when method is used in the future
//    @Test
//    void CanAfford() {
//    }

    @Test
    void getPrice() {
        assert car.getPrice() == 25895;
    }

    @Test
    void getBrand() {
        assert car.getBrand().equals("Infiniti QX50");
    }

    @Test
    void getYear() {
        assert car.getYear().equals("2016");
    }

    @Test
    void getKMS() {
        assert car.getKMS().equals("71935");
    }

    @Test
    void returnID() {
        assert car.getID().equals("24");
    }

// Will implement test when method is used in the future
//    @Test
//    void show() {
//    }
}