package lists;

import lists.nodes.DNode;

public class DoublyLinkedList<DataType> extends List {
    //methods
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
    public DataType get(int index) {
        if (index >= super.nodes) return null;
        if (index < super.nodes / 2)
            return (DataType) getNodeFromHead(index).getData();
        return (DataType) getNodeFromTail(index).getData();
    }

    private DNode getNodeFromTail(int index) {
        DNode latest = (DNode) tail;
        for (int i = 0; i < super.nodes - index - 1; i++) {
            latest = latest.getPrevLink();
        }
        return latest;
    }
}