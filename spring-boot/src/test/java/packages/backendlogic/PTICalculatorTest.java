package packages.backendlogic;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.SQLException;

public class PTICalculatorTest {

    int user_id;
    Car c;
    PTICalculator pti;

    @BeforeEach
    void setUp() throws SQLException, IOException, InterruptedException {
        user_id = 6;
        c = new Car("2021", "Mazda CS-5 GT", "8923", "Convertible",
                "2", "40779", "Dixie Mazdda");
        pti = new PTICalculator(user_id, c);
    }

    @Test
    void getUserID(){
        assert user_id == pti.getUserID();
    }

    @Test
    void getCar(){
        assert c.equals(pti.getCar());
    }

    @Test
    void getPTI(){
        System.out.println(pti.getPTI());
    }
}
