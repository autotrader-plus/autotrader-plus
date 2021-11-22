package packages.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensoConnectionFailureExceptionTest {
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(SensoConnectionFailureException.class, () -> {
            throw new SensoConnectionFailureException();
        });

        System.out.println(exception.getMessage());

        String expectedMessage = "Connection to Senso API fails.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
        }
}