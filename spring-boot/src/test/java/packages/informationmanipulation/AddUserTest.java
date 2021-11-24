package packages.informationmanipulation;

import packages.backendlogic.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

class AddUserTest {

    @Test
    @DisplayName("addUser: Basic Case with Simple User")
    void addUserSimple() throws SQLException {
        User user = new User(740, 1000, 5000, "M4Y111", "Mike");

        AddUser.addUser(user);

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "2");
        testMap.put("Name", "Mike");
        testMap.put("Credit Score", "740");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        testMap.put("Monthly Income", "0");
        testMap.put("Employment Status", "not Employed");
        testMap.put("Homeowner", "not Homeowner");
        testMap.put("Monthly Debt Obligation", "0");

        System.out.print(ReturnUserInformation.returnUser(2));

        assert testMap.equals(ReturnUserInformation.returnUser(2));
    }

    @Test
    @DisplayName("addUser: Basic Case with Detailed User")
    void addUserDetailed() throws SQLException {
        User user = new User(730, 1000, 5000, "M4Y111", "Mike",
                8500, true, true, 500);

        AddUser.addUser(user);

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "1");
        testMap.put("Name", "Mike");
        testMap.put("Credit Score", "730");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        testMap.put("Monthly Income", "8500");
        testMap.put("Employment Status", "Employed");
        testMap.put("Homeowner", "Homeowner");
        testMap.put("Monthly Debt Obligation", "500");

        assert testMap.equals(ReturnUserInformation.returnUser(1));
    }

}