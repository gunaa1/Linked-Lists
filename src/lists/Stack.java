package lists;

import lists.nodes.SNode;

public class Stack<DataType> extends List<DataType> {
    //methods
    @Override
    public void add(DataType object) {
        SNode newNode = new SNode(object);
        if (super.head != null) {
            newNode.setNextLink(super.head);
        }
        super.head = newNode;
    }

    public DataType pop() {
        if (super.nodes == 0) return null;
        DataType data = (DataType) head.getData();
        head = head.getNextLink();
        super.nodes--;
        return data;
    }

    public DataType peek() {
        if (head == null) return null;
        return ((DataType) head.getData());
    }

    //default printing method
    @Override
    public String toString() {
        SNode node = head;
        String string = "Stack: ";
        while (node != null) {
            string += node.getData() + " ";
            node = node.getNextLink();
        }
        return string;
    }
}
