package array_based.exceptions;

import java.util.Queue;

public class QueueOverflowException extends Exception {
    //constructor
    public QueueOverflowException() {
        super("Queue is full!");
    }
}
