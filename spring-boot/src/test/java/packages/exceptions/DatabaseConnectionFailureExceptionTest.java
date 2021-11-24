package packages.exceptions;

import org.junit.jupiter.api.Test;
import packages.returninfo.ReturnDatabaseInfo;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionFailureExceptionTest {
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(DatabaseConnectionFailureException.class, () -> {
            throw new DatabaseConnectionFailureException();
        });

        System.out.println(exception.getMessage());

        String expectedMessage = "Connection to TraderAuto Database fails.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}