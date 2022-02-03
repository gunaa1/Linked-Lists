package node_based.exceptions;

public class ElementNotFoundException extends Exception {
    //contructor
    public ElementNotFoundException() {
        super("Could not find specified element!");
    }
}