package packages.database_info_manipulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import packages.exceptions.DatabaseConnectionFailureException;

import static org.mockito.Mockito.*;

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
    void checkUserSuccessMocked() throws DatabaseConnectionFailureException {
        mockedConnection.checkUser("Paul", "123");
        when(mockedConnection.checkUser("Paul", "123")).thenReturn(createResponse("Success"));

        boolean actual = authenticator.checkUser("Paul", "123");
        assert (actual == mockedConnection.checkUser("Paul", "123"));
    }

    @Test
    @DisplayName("Unit Testing with Mocked Connection - Failed Case")
    void checkUserFailedMocked() throws DatabaseConnectionFailureException {
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
    void checkUserSuccess() throws DatabaseConnectionFailureException {
        assert authenticator.checkUser("Paul", "123");
    }

    @Test
    @DisplayName("Integration Testing - Failed Case")
    void checkUserFailed() throws DatabaseConnectionFailureException {
        assert !authenticator.checkUser("DNE", "DNE");
    }

}
