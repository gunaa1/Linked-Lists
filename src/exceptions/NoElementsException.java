package exceptions;

public class NoElementsException extends Exception {
    //constructor
    public NoElementsException() {
        super("No elements to pop!");
    }
}
