package exceptions;

public class ReductionRefactorException extends Exception {
    // constructor
    public ReductionRefactorException() {
        super("The new max size array will not be able to hold all current elements!");
    }
}