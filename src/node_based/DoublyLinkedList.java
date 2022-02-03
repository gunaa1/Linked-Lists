package node_based;

import node_based.nodes.DNode;

public class DoublyLinkedList<DataType> extends List<DataType> {
    // methods
    // adds a new data point at the end of the list
    public void add(DataType object) {
        DNode newNode = new DNode(object);
        if (head == null) {
            head = newNode;
        }
        else {
            tail.setNextLink(newNode);
            newNode.setPrevLink((DNode) tail);
        }
        tail = newNode;
        super.size++;
    }

    //gets the data at the specified index efficiently based on node's location
    public DataType get(int index) {
        return (DataType) getNode(index).getData();
    }

    // gets the node at the specifed index
    private DNode getNode(int index) {
        if ((index >= super.size) || (index < 0)) throw new IndexOutOfBoundsException("Please enter a valid index!");
        if (index >= super.size) return null;
        if (index < super.size / 2)
            return (DNode) getNodeFromHead(index);
        return getNodeFromTail(index);
    }

    private DNode getNodeFromHead(int index) {
        DNode latest = (DNode) super.head;
        for (int i = 0; i < index; i++)  {
            latest = (DNode) latest.getNextLink();
        }
        return latest;
    }

    private DNode getNodeFromTail(int index) {
        DNode latest = (DNode) super.tail;
        for (int i = 0; i < super.size - index - 1; i++) {
            latest = latest.getPrevLink();
        }
        return latest;
    }

    //removes a node at specified element
    public DNode remove(int index) {
        assert(index >= 0);
        if (index >= super.size) return null;

        DNode current = getNode(index);

        if (current.getPrevLink() != null) {
            current.getPrevLink().setNextLink(current.getNextLink());
            current.setPrevLink(null);
        }
        if (current.getNextLink() != null) {
            ((DNode) current.getNextLink()).setPrevLink(current.getPrevLink());
            current.setNextLink(null);
        }

        super.size--;
        return current;
    }
}