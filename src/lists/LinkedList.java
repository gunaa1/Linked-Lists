package lists;

import lists.nodes.Node;

public class LinkedList<DataType> extends List {
    //constructor
    public LinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    //getters and setters
    public int getLength() {
        return this.length;
    }
}