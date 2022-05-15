package exceptions;

public class QueueOverflowException extends Exception {
    //constructor
    public QueueOverflowException() {
        super("Queue is full!");
    }
}
