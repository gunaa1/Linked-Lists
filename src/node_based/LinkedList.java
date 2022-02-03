package node_based;

import node_based.nodes.SNode;

public class LinkedList<DataType> extends List<DataType> {
    // methods
    // adds a new data point to the list
    public void add(DataType data) {
        SNode newNode = new SNode(data);
        if (super.size == 0) {
            super.head = newNode;
        }
        else {
            super.tail.setNextLink(newNode);
        }
        super.tail = newNode;
        super.size++;
    }

    // returns the data located at the specified index
    public DataType get(int index) {
        return (DataType) this.getNode(index).getData();
    }

    // returns the node at the specified index
    private SNode getNode(int index) throws IndexOutOfBoundsException {
        if ((index >= super.size) || (index < 0)) throw new IndexOutOfBoundsException("Please enter a valid index!");
        SNode latest = super.head;
        for (int i = 0; i < index; i++) {
            latest = latest.getNextLink();
        }
        return latest;
    }

    //removes the node at the specified index 
    public SNode remove(int index) {
        assert(index >= 0);
        if (index >= super.size) return null;

        SNode current = super.head, prev = null;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.getNextLink();
        }

        if (prev != null) {
            prev.setNextLink(current.getNextLink());
        }
        current.setNextLink(null);

        super.size--;
        return current;
    }
}