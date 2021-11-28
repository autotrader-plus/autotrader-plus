package packages.informationmanipulation;

import org.junit.jupiter.api.BeforeEach;
import packages.backendlogic.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

class AddUserTest {
    ReturnUserInformation returnUserInformation;
    AddUser addUser;

    @BeforeEach
    void setUp(){
        returnUserInformation = new ReturnUserInformation();
        addUser = new AddUser();
    }

    @Test
    @DisplayName("addUser: Basic Case with Simple User")
    void addUserSimple() throws SQLException {
        HashMap<String, String> user = new HashMap<>();
        user.put("creditscore", "740");
        user.put("monthlyBudget", "1000");
        user.put("downPayment", "5000");
        user.put("zipCode", "M4Y111");
        user.put("name", "Paul");
        user.put("password", "123");

        addUser.addUser(user);

        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("ID", "2");
        testMap.put("Name", "Paul");
        testMap.put("Credit Score", "740");
        testMap.put("Location", "M4Y111");
        testMap.put("Max Downpayment", "5000");
        testMap.put("Max Monthly Payment", "1000");
        testMap.put("Monthly Income", "0");
        testMap.put("Employment Status", "not Employed");
        testMap.put("Homeowner", "not Homeowner");
        testMap.put("Monthly Debt Obligation", "0");
        testMap.put("password", "123");

        assert testMap.equals(returnUserInformation.returnUser(2));
    }

    @Test
    @DisplayName("addUser: Basic Case with Detailed User")
    void addUserDetailed() throws SQLException {
        new User(730, 1000, 5000, "M4Y111", "Mike",
                "1234", 8500, true, true, 500);

        HashMap<String, String> user = new HashMap<>();
        user.put("creditscore", "730");
        user.put("monthlyBudget", "1000");
        user.put("downPayment", "5000");
        user.put("zipCode", "M4Y111");
        user.put("name", "Mike");
        user.put("password", "1234");
        user.put("monthlyIncome", "8500");
        user.put("employed", "employed");
        user.put("homeowner", "homeowner");
        user.put("monthlyDebt", "500");
        addUser.addUser(user);

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
        testMap.put("password", "1234");

        assert testMap.equals(returnUserInformation.returnUser(1));
    }

}