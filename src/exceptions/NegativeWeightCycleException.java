package exceptions;

public class NegativeWeightCycleException extends Exception {
    //contructor
    public NegativeWeightCycleException() {
        super("There is a negative weight cycle, which leads to a negative infinity shortest path!");
    }
}