package exceptions;

/**
 * DataBaseException is a custom exception that is thrown when the inventory system is not reachable.
 */
public class DataBaseException extends Exception {
    /**
     * Constructs a new DataBaseException with a default error message.
     *
     * @param message The detailed message for the exception.
     */
    public DataBaseException(String message) {
        super(message);
    }
}
