package lists;

import lists.nodes.SNode;

public class LinkedList<DataType> extends List<DataType> {
    //methods
    public DataType getData(int index) {
        return (DataType) this.getNode(index).getData();
    }

    public SNode getNode(int index) {
        if (index >= super.nodes) return null;
        return getNodeFromHead(index);
    }

    //removes the node at the specified index 
    public SNode removeNodeAtIndex(int index) {
        assert(index >= 0);
        if (index >= super.nodes) return null;

        SNode current = super.head, prev = null;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.getNextLink();
        }

        if (prev != null) {
            prev.setNextLink(current.getNextLink());
        }
        current.setNextLink(null);

        super.nodes--;
        return current;
    }
}