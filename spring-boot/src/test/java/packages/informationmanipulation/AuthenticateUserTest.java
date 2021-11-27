package packages.informationmanipulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import packages.informationmanipulation.AuthenticateUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;

public class AuthenticateUserTest {
    AuthenticateUser authenticator;
    AuthenticateUser mockedConnection;

    @BeforeEach
    void setUp() {
        authenticator = new AuthenticateUser();
        mockedConnection = mock(AuthenticateUser.class);
    }

    @Test
    @DisplayName("Unit Testing with Mocked Connection - Success Case")
    void checkUserSuccessMocked() throws SQLException{
        mockedConnection.checkUser("Lord Pikachu", "GottaCatchThemAll");
        when(mockedConnection.checkUser("Lord Pikachu", "GottaCatchThemAll")).thenReturn(createResponse("Success"));

        boolean actual = authenticator.checkUser("Lord Pikachu", "GottaCatchThemAll");
        assert (actual == mockedConnection.checkUser("Lord Pikachu", "GottaCatchThemAll"));
    }

    @Test
    @DisplayName("Unit Testing with Mocked Connection - Failed Case")
    void checkUserFailedMocked() throws SQLException{
        mockedConnection.checkUser("DNE", "DNE");
        when(mockedConnection.checkUser("DNE", "DNE")).thenReturn(createResponse("Failure"));

        boolean actual = authenticator.checkUser("DNE", "DNE");
        assert (actual == mockedConnection.checkUser("DNE", "DNE"));
    }

    private Boolean createResponse(String condition) {
        if (condition == "Success"){
            return true;
        } else{
            return false;
        }
    }

    @Test
    @DisplayName("Integration Testing - Success Case")
    void checkUserSuccess() throws SQLException {
        assert authenticator.checkUser("Lord Pikachu", "GottaCatchThemAll");
    }

    @Test
    @DisplayName("Integration Testing - Failed Case")
    void checkUserFailed() throws SQLException {
        assert !authenticator.checkUser("DNE", "DNE");
    }

}
