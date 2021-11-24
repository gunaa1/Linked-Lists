package lists;

import lists.nodes.SNode;

public class LinkedList<DataType> extends List {
    //methods
    public void add(DataType object) {
        SNode newNode = new SNode<DataType>(object);
        if (super.head == null) {
            super.head = newNode;
        }
        else {
            super.tail.setNextLink(newNode);
        }
        super.tail = newNode;
        super.nodes++;
    }

    public DataType get(int index) {
        if (index >= super.nodes) return null;
        return (DataType) getNodeFromHead(index).getData();
    }
}