package exceptions;

public class DataBaseException extends Exception {
    public DataBaseException(String message) {
        super("The Inventory is not reachable");
    }
}
