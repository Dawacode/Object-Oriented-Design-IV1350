package exceptions;

/**
 * ItemException is a custom exception that is thrown when an item cannot be found.
 */
public class ItemException extends Exception {
    /**
     * Constructs a new ItemException with a default error message.
     *
     * @param message The detailed message for the exception.
     */
    public ItemException(String message) {
        super("The item could not be found");
    }
}
