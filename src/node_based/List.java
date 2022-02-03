package node_based;

import node_based.nodes.SNode;

public abstract class List<DataType> {
    //attributes
    protected SNode head, tail;
    protected int size;

    //constructor
    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //getters and setters
    public int getSize() {
        return this.size;
    }

    //methods
    
    public boolean isEmpty() {
        return (this.size == 0);
    }

    //default print method
    public String toString() {
        String toPrint = "Linked List: ";
        SNode latest = head;
        while (latest.getNextLink() != null) {
            toPrint += latest.getData() + "\t";
            latest = latest.getNextLink();
        }
        return toPrint;
    }
}
