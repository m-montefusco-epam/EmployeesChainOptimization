package EmployeesChainOptimization.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReaderExceptionTest {
    @Test
    public void testExceptionMessage() {
        String message = "Test message";
        ReaderException readerException = new ReaderException(message);

        assertEquals("Exception message should be equal to the input message", message, readerException.getMessage());
    }

    @Test
    public void testExceptionMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        ReaderException readerException = new ReaderException(message, cause);

        assertEquals("Exception message should be equal to the input message", message, readerException.getMessage());
        assertEquals("Cause should be equal to the input cause", cause, readerException.getCause());
    }
}
