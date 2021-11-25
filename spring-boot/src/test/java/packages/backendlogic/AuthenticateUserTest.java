package packages.backendlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AuthenticateUserTest {
    AuthenticateUser auth1;
    AuthenticateUser auth2;

    @BeforeEach
    void setUp() {
        auth1 = new AuthenticateUser();
        auth2 = new AuthenticateUser();
    }

    @Test
    void checkUser1() throws SQLException {
        assert auth1.checkUser("Lord Pikachu", "GottaCatchThemAll");
    }

    @Test
    void checkUser2() throws SQLException {
        assert !auth2.checkUser("DNE", "DNE");
    }

}
