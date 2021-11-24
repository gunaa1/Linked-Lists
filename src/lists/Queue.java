package lists;

import lists.nodes.DNode;

public class Queue<DataType> {
    //attributes
    private DNode head, tail;

    //contructors
    public Queue() {
        head = null;
        tail = null;
    }

    //methods
    public void add(DataType data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            head = newNode;
        }
        else {
            tail.setNextLink(newNode);
            newNode.setPrevLink(tail);
        }
        tail = newNode;
    }

    public DataType poll() {
        if (head == null) return null;
        DataType data = ((DataType) head.getData());
        head = head.getNextLink();
        return data;
    }

    public DataType peek() {
        if (head == null) return null;
        return ((DataType) head.getData());
    }

    public String toString() {
        DNode node = head;
        String string = "Queue: ";
        while (node != null) {
            string += node.getData() + " ";
            node = node.getNextLink();
        }
        return string;
    }
}
