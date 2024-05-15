package exceptions;

public class DataBaseException extends Exception {
    public DataBaseException(String message) {
        super("The data base is not up and running");
    }
}
