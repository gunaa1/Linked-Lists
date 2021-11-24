package lists;

import lists.nodes.SNode;

public abstract class List<DataType> {
    //attributes
    protected SNode head, tail;
    protected int nodes;

    //constructor
    public List() {
        head = null;
        tail = null;
        nodes = 0;
    }

    //getters and setters
    public int getSize() {
        return this.nodes;
    }

    //methods
    public void add(DataType data) {
        SNode newNode = new SNode(data);
        if (this.nodes == 0) {
            this.head = newNode;
        }
        else {
            this.tail.setNextLink(newNode);
        }
        this.tail = newNode;
        this.nodes++;
    }

    protected SNode getNodeFromHead(int index) {
        SNode latest = head;
        for (int i = 0; i <= index; i++) {
            latest = latest.getNextLink();
        }
        return latest;
    }
    
    public boolean isEmpty() {
        return ((this.nodes == 0) ? true: false);
    }

    //default print method
    public String toString() {
        String toPrint = "Linked List: ";
        SNode latest = head;
        while (latest.getNextLink() != null) {
            toPrint += latest.getData() + " ";
            latest = latest.getNextLink();
        }
        return toPrint;
    }
}
