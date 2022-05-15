package node_based;


// Imports
import exceptions.ElementNotFoundException;
import node_based.nodes.SNode;


public class LinkedList<DataType> extends List<DataType> {
    // ADT Methods
    public void add(DataType data) {
        SNode newNode = new SNode(data);
        if (super.size == 0)
            super.head = newNode;
        else
            super.tail.setNextLink(newNode);
        super.tail = newNode;
        super.size++;
    }

    public DataType getData(int index) {
        return (DataType) this.getNode(index).getData();
    }

    private SNode getNode(int index) throws IndexOutOfBoundsException {
        if ((index >= super.size) || (index < 0)) throw new IndexOutOfBoundsException();
        SNode latest = super.head;
        for (int i = 0; i < index; i++)
            latest = latest.getNextLink();
        return latest;
    }

    public SNode remove(DataType data) throws ElementNotFoundException {
        SNode curNode = this.head, prevNode = null;
        while ((curNode != null) && (!curNode.getData().equals(data))) {
            prevNode = curNode;
            curNode = curNode.getNextLink();
        }

        if (curNode == null) throw new ElementNotFoundException();
        
        if (prevNode != null)
            prevNode.setNextLink(curNode.getNextLink());
        curNode.setNextLink(null);
        super.size--;
        return curNode;
    }

    public void reverseList() {
        SNode prevNode = null, curNode = this.head, nextNode = null;
        while (curNode != null) {
            nextNode = curNode.getNextLink();
            curNode.setNextLink(prevNode);
            prevNode = curNode;
            curNode = nextNode;
        }
        this.head = prevNode;
    }
}