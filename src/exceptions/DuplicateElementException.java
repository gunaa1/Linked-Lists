package exceptions;

public class DuplicateElementException extends Exception {
    //contructor
    public DuplicateElementException() {
        super("This element already exists!");
    }
}