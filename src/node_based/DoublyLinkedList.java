package node_based;


// Imports
import exceptions.ElementNotFoundException;
import node_based.nodes.DNode;


public class DoublyLinkedList<DataType extends Comparable<DataType>> extends List<DataType> {
    // ADT Methods
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

    public void sortedAdd(DataType childData) {
        super.size++;
        DNode newNode = new DNode(childData);
        if (head == null) {
            head = newNode;
        }
        else {
            DNode traversalNode = (DNode) this.head;
            while ((traversalNode != null) && (((DataType) traversalNode.getData()).compareTo(childData) < 0)) {
                traversalNode = (DNode) traversalNode.getNextLink();
            }
            if (traversalNode == null) {
                this.tail.setNextLink(newNode);
                newNode.setPrevLink((DNode) this.tail);
                this.tail = newNode;
            }
            else {
                if (traversalNode.getPrevLink() != null) {
                    traversalNode.getPrevLink().setNextLink(newNode);
                    newNode.setPrevLink(traversalNode.getPrevLink());
                }
                newNode.setNextLink(traversalNode);
                traversalNode.setPrevLink(newNode);
            }
        }
    }

    public DataType get(int index) {
        return (DataType) getNode(index).getData();
    }

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

    public DNode remove(DataType data) throws ElementNotFoundException {
        DNode curNode = (DNode) this.head;
        while ((curNode != null) && (!curNode.getData().equals(data)))
            curNode = (DNode) curNode.getNextLink();

        if (curNode == null) throw new ElementNotFoundException();
        
        if (curNode.getPrevLink() != null)
            curNode.getPrevLink().setNextLink(curNode.getNextLink());
        if (curNode.getNextLink() != null)
            ((DNode) curNode.getNextLink()).setPrevLink(curNode.getPrevLink());
        curNode.setNextLink(null);
        curNode.setPrevLink(null);
        
        super.size--;
        return curNode;
    }

    public void reverseList() {
        DNode temp = (DNode) this.head;
        this.head = this.tail;
        this.tail = temp;
    }
}