package packages.exceptions;

public class SensoConnectionFailureException extends Exception {
    /**
     * Exception class for Senso API connection failures.
     */
    public SensoConnectionFailureException(){
        super("Connection to Senso API fails.");
    }
}
