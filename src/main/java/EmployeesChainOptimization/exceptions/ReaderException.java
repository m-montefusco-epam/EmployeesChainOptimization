package EmployeesChainOptimization.exceptions;

public class ReaderException extends Exception{
    public ReaderException(String msg) {
        super(msg);
    }

    public ReaderException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
