package lists;

import lists.nodes.Node;

public class LinkedList<DataType> {
    //attributes
    private Node head, tail;
    private int length;

    //constructor
    public LinkedList() {
        head = null;
        tail = null;
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
        String toPrint = "";
        Node latest = head;
        while (latest.getNextLink() != null) {
            toPrint += latest.getData() + " ";
            latest = latest.getNextLink();
        }
        return toPrint;
    }
}
