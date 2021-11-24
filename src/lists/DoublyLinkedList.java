package lists;

import lists.nodes.DNode;

public class DoublyLinkedList<DataType> extends List<DataType> {
    //methods
    @Override
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
        super.nodes++;
    }

    //gets the node at any index and does so efficiently based on where the node is located
    public DataType getData(int index) {
        return (DataType) getNode(index).getData();
    }
    
    public DNode getNode(int index) {
        if (index >= super.nodes) return null;
        if (index < super.nodes / 2)
            return (DNode) getNodeFromHead(index);
        return getNodeFromTail(index);
    }

    private DNode getNodeFromTail(int index) {
        DNode latest = (DNode) tail;
        for (int i = 0; i < super.nodes - index - 1; i++) {
            latest = latest.getPrevLink();
        }
        return latest;
    }

    //removes a node at specified element
    public DNode removeNodeAtIndex(int index) {
        assert(index >= 0);
        if (index >= super.nodes) return null;

        DNode current = this.getNode(index);

        if (current.getPrevLink() != null) {
            current.getPrevLink().setNextLink(current.getNextLink());
            current.setPrevLink(null);
        }
        if (current.getNextLink() != null) {
            ((DNode) current.getNextLink()).setPrevLink(current.getPrevLink());
            current.setNextLink(null);
        }

        super.nodes--;
        return current;
    }
}