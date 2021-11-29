package packages.informationmanipulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

class ReturnUserInformationTest {
    ReturnUserInformation returnUserInformation;
    ReturnUserInformation mockedConnection;

    @BeforeEach
    void setUp() {
        returnUserInformation = new ReturnUserInformation();
        mockedConnection = mock(ReturnUserInformation.class);
    }

    @Test
    @DisplayName("User: Basic Case with Simple User (Unit Test with Mocked Database)")
    void returnUserSimpleMocked() throws SQLException{
        mockedConnection.returnUser(2);
        when(mockedConnection.returnUser(2)).thenReturn(createSimpleResponse());

        HashMap<String, String> actual = returnUserInformation.returnUser(2);
        assert Objects.equals(actual, mockedConnection.returnUser(2));

    }

    private HashMap<String, String> createSimpleResponse() {
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
        return testMap;
    }

    @Test
    @DisplayName("User: Basic Case with Detailed User (Unit Test with Mocked Database)")
    void returnUserDetailedMocked() throws SQLException{
        mockedConnection.returnUser(1);
        when(mockedConnection.returnUser(1)).thenReturn(createDetailedResponse());

        HashMap<String, String> actual = returnUserInformation.returnUser(1);
        assert Objects.equals(actual, mockedConnection.returnUser(1));

    }

    private HashMap<String, String> createDetailedResponse() {
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
        return testMap;
    }

    @Test
    @DisplayName("User: Basic Case with Simple User (Integration Testing)")
    void returnUserSimple() throws SQLException {
        Integer user_id = 2;

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

        assert Objects.equals(testMap, returnUserInformation.returnUser(user_id));
    }

    @Test
    @DisplayName("User: Basic Case with Detailed User (Integration Testing)")
    void returnUserDetailed() throws SQLException {
        Integer user_id = 1;

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

        assert Objects.equals(testMap, returnUserInformation.returnUser(user_id));
    }

}