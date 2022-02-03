package node_based.exceptions;

public class NoParentException extends Exception {
    //contructor
    public NoParentException() {
        super("This element is the root and therefore, does not have a parent!");
    }
}