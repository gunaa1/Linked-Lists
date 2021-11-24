package lists;

import lists.nodes.Node;

public class Stack<DataType> {
    //attributes
    private Node head;
    private int length;

    //contructors
    public Stack() {
        head = null;
        length = 0;
    }

    //methods
    public void add(DataType object) {
        Node newNode = new Node(object);
        if (head != null) {
            newNode.setNextLink(head);
        }
        head = newNode;
        length++;
    }

    public DataType pop() {
        if (head == null) return null;
        DataType data = (DataType) head.getData();
        head = head.getNextLink();
        return data;
    }

    public DataType peek() {
        if (head == null) return null;
        return ((DataType) head.getData());
    }

    public boolean isEmpty() {
        return (head == null);
    }

    //default printing method
    public String toString() {
        Node node = head;
        String string = "Stack: ";
        while (node != null) {
            string += node.getData() + " ";
            node = node.getNextLink();
        }
        return string;
    }
}
