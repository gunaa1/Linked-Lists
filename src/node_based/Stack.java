package node_based;


// Imports
import node_based.nodes.SNode;


public class Stack<DataType> extends List<DataType> {
    // ADT Methods
    public void add(DataType object) {
        SNode newNode = new SNode(object);
        if (super.head != null)
            newNode.setNextLink(super.head);
        super.head = newNode;
    }

    public DataType pop() {
        if (super.size == 0) return null;
        DataType data = (DataType) head.getData();
        head = head.getNextLink();
        super.size--;
        return data;
    }

    public DataType peek() {
        if (head == null) return null;
        return ((DataType) head.getData());
    }

    
    // Default Methods
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