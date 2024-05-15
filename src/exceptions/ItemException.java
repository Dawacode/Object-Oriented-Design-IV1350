package exceptions;

public class ItemException extends Exception {
    public ItemException(String message) {
        super("The item could not be found");
    }

}
