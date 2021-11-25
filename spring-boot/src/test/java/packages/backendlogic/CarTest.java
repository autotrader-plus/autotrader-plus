package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

    Car c1;

    @BeforeEach
    void setUp() {
        c1 = new Car("2016", "Infiniti QX50", "71935", "Wagon",
                "24", "25895", "Dixie Infiniti");
    }

// Will implement test when method is used in the future
//    @Test
//    void CanAfford() {
//    }

    @Test
    void getPrice() {
        assert c1.getPrice() == 25895;
    }

    @Test
    void getBrand() {
        assert c1.getBrand().equals("Infiniti QX50");
    }

    @Test
    void getYear() {
        assert c1.getYear().equals("2016");
    }

    @Test
    void getKMS() {
        assert c1.getKMS().equals("71935");
    }

    @Test
    void returnID() {
        assert c1.getID().equals("24");
    }

// Will implement test when method is used in the future
//    @Test
//    void show() {
//    }
}