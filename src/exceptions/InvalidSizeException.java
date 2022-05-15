package exceptions;

public class InvalidSizeException extends Exception {
    //constructor
    public InvalidSizeException() {
        super("Please provide a valid refactor size!");
    }
}
