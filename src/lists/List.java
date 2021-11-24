package lists;

import lists.nodes.Node;

public abstract class List<DataType> {
    //attributes
    protected Node head;
    protected String toPrint;
    protected int length;

    //constructor
    public List() {
        head = null;
        toPrint = "";
        length = 0;
    }

    //getters and setters
    public int getLength() {
        return this.length;
    }

    //methods
    public boolean isEmpty() {
        return (head == null);
    }

    //default print method
    public String toString() {
        Node latest = head;
        while (latest.getNextLink() != null) {
            toPrint += latest.getData() + " ";
            latest = latest.getNextLink();
        }
        return toPrint;
    }
}
