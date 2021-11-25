package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AuthenticateUserTest {
    AuthenticateUser authenticator;

    @BeforeEach
    void setUp() {
        authenticator = new AuthenticateUser();
    }

    @Test
    void checkUserSuccess() throws SQLException {
        assert authenticator.checkUser("Lord Pikachu", "GottaCatchThemAll");
    }

    @Test
    void checkUserFailed() throws SQLException {
        assert !authenticator.checkUser("DNE", "DNE");
    }

}
