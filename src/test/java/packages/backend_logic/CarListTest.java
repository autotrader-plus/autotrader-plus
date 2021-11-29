package packages.backend_logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarListTest {

    CarList<Car> testCarList;
    Car car;

    @BeforeEach
    void setUp() {
        testCarList = new CarList<>();

        car = new Car("2016", "Infiniti QX50", "70953", "Wagon",
                "24", "25900", "Dixie Infiniti");
    }

    @Test
    void AddToList() {
        testCarList.AddtoList(car);
        assert testCarList.getCar(0).equals(car);
    }

    @Test
    void sizeEmpty() {
        assert testCarList.size() == 0;
    }

    @Test
    void sizeNonEmpty() {
        testCarList.AddtoList(car);
        assert testCarList.size() == 1;
    }

    @Test
    void getCar() {
        testCarList.AddtoList(car);
        assert testCarList.getCar(0).equals(car);
    }
}