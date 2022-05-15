package node_based;


// Imports
import node_based.nodes.SNode;


public abstract class List<DataType> {
    // Attributes
    protected SNode head, tail;
    protected int size;


    // Constructor
    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Getters
    public int getSize() {
        return this.size;
    }


    // ADT Methods
    public boolean isEmpty() {
        return (this.size == 0);
    }

    // Default Method
    public String toString() {
        String toPrint = "Linked List: ";
        SNode latest = head;
        while (latest != null) {
            toPrint += latest.getData() + " ";
            latest = latest.getNextLink();
        }
        toPrint += "\n";
        return toPrint;
    }
}
