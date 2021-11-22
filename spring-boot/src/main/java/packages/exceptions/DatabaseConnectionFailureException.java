package packages.exceptions;

public class DatabaseConnectionFailureException extends Exception{
    /**
     * Exception class for Database connection failure.
     */
    public DatabaseConnectionFailureException(){
        super("Connection to TraderAuto Database fails.");
    }
}
