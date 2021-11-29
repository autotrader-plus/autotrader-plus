package packages.informationmanipulation;

import org.junit.jupiter.api.BeforeEach;
import packages.backendlogic.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.HashMap;

class AddUserTest {
    ReturnUserInformation returnUserInformation;
    AddUser addUser;
    AddUser mockedConnection;
    HashMap<String, String> basicUser;
    HashMap<String, String> advancedUser;

    @BeforeEach
    void setUp(){
        returnUserInformation = new ReturnUserInformation();
        addUser = new AddUser();
        mockedConnection = mock(AddUser.class);

        HashMap<String, String> basic = new HashMap<>();
        basic.put("credit-score", "740");
        basic.put("monthlybudget", "1000");
        basic.put("downpayment", "5000");
        basic.put("zip-code", "M4Y111");
        basic.put("name", "Paul");
        basic.put("password", "123");
        basicUser = basic;

        HashMap<String, String> advanced = new HashMap<>();
        advanced.put("credit-score", "730");
        advanced.put("monthlybudget", "1000");
        advanced.put("downpayment", "5000");
        advanced.put("zip-code", "M4Y111");
        advanced.put("name", "Mike");
        advanced.put("password", "1234");
        advanced.put("monthlyincome", "8500");
        advanced.put("employed", "employed");
        advanced.put("homeowner", "homeowner");
        advanced.put("monthlydebt", "500");
        advancedUser = advanced;
    }

    @Test
    @DisplayName("addUser: Simple User (Unit Test with Mocked Database")
    void addUserSimpleMocked() throws SQLException{
        mockedConnection.addUser(basicUser);
        when(mockedConnection.addUser(basicUser)).thenReturn("Successful");
        verify(mockedConnection,times(1)).addUser(basicUser);
    }

    @Test
    @DisplayName("addUser: Detailed User (Unit Test with Mocked Database")
    void addUserDetailedMocked() throws SQLException{
        mockedConnection.addUser(advancedUser);
        when(mockedConnection.addUser(advancedUser)).thenReturn("Successful");
        verify(mockedConnection,times(1)).addUser(advancedUser);
    }

    @Test
    @DisplayName("addUser: Simple User (Integration Test)")
    void addUserSimple() throws SQLException {
        addUser.addUser(basicUser);

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
    @DisplayName("addUser: Detailed User (Integration Test)")
    void addUserDetailed() throws SQLException {
        addUser.addUser(advancedUser);

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