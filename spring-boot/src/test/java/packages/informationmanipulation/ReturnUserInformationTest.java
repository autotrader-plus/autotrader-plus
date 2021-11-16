package packages.informationmanipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

class ReturnUserInformationTest {

    @Test
    @DisplayName("User: Basic Case with Simple User")
    void returnUserSimple() throws SQLException {
        Integer user_id = 8;

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "8");
        testMap.put("Name", "Mike");
        testMap.put("Credit Score", "730");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        testMap.put("Monthly Income", "0");
        testMap.put("Employment Status", "not Employed");
        testMap.put("Homeowner", "not Homeowner");
        testMap.put("Monthly Debt Obligation", "0");

        assert Objects.equals(testMap, ReturnUserInformation.returnUser(user_id));
    }

    @Test
    @DisplayName("User: Basic Case with Detailed User")
    void returnUserDetailed() throws SQLException {
        Integer user_id = 1;

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "1");
        testMap.put("Name", "Ameen");
        testMap.put("Credit Score", "750");
        testMap.put("Location", "M5V0P5");
        testMap.put("Max Downpayment", "150");
        testMap.put("Max Monthly Payment", "2000");
        testMap.put("Monthly Income", "1000");
        testMap.put("Employment Status", "true");
        testMap.put("Homeowner", "false");
        testMap.put("Monthly Debt Obligation", "1000");

        assert Objects.equals(testMap, ReturnUserInformation.returnUser(user_id));
    }

}