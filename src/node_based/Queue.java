package node_based;


// Imports
import node_based.nodes.SNode;


public class Queue<DataType> extends LinkedList<DataType> {
    // ADT Methods
    public DataType poll() {
        if (super.size == 0) return null;
        DataType data = ((DataType) super.head.getData());
        super.head = super.head.getNextLink();
        super.size--;
        return data;
    }

    public DataType peek() {
        if (head == null) return null;
        return ((DataType) super.head.getData());
    }

    
    // Default Methods
    @Override
    public String toString() {
        SNode node = head;
        String string = "Queue: ";
        while (node != null) {
            string += node.getData() + " ";
            node = node.getNextLink();
        }
        return string;
    }
}
