package packages.returninfo;

import org.junit.jupiter.api.Test;
import packages.responseformatting.HttpResponseMain;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ReturnDatabaseInfoTest {

    @Test
    void getContent() throws SQLException {
        ReturnDatabaseInfo test = new ReturnDatabaseInfo();
        String expected = "ID: 1\n" +
                "Car: Hyundai Sonata Ultimate\n" +
                "Cost: $35649\n" +
                "Mileage: 10323\n" +
                "Model Year: 2021\n" +
                "Dealership: Guelph Hyundai\n" +
                "Car Type: Convertible";
        assert (Objects.equals(test.getContent(), expected));
    }

    @Test
    void httpResponseMain() throws SQLException {
        ReturnDatabaseInfo test = new ReturnDatabaseInfo();
        System.out.print(test.httpResponseMain("").getClass());
        assert(test.httpResponseMain("") instanceof HttpResponseMain);
    }
}