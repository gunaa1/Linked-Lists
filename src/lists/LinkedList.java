package lists;

public class LinkedList<DataType> {
    //attributes
    private Node head, tail;
    private int length;

    public LinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    //getters and setters
    public int getLength() {
        return this.length;
    }

    //methods
    public void add(DataType object) {
        Node newNode = new Node(object);
        if (head == null) {
            head = newNode;
        }
        else {
            tail.setNextLink(newNode);
            newNode.setPrevLink(tail);
        }
        tail = newNode;
        length++;
    }

    //gets the node at any index and does so efficiently based on where the node is located
    public DataType get(int index) {
        if (index >= length) return null;
        if (index < length / 2)
            return (DataType) getNodeFromHead(index).getData();
        return (DataType) getNodeFromTail(index).getData();
    }

    private Node getNodeFromHead(int index) {
        Node latest = head;
        for (int i = 0; i <= index; i++) {
            latest = latest.getNextLink();
        }
        return latest;
    }

    private Node getNodeFromTail(int index) {
        Node latest = tail;
        for (int i = 0; i < length - index - 1; i++) {
            latest = latest.getPrevLink();
        }
        return latest;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    //default print method
    public String toString() {
        String toPrint = "";
        Node latest = head;
        while (latest.getNextLink() != null) {
            toPrint += latest.getData() + " ";
            latest = latest.getNextLink();
        }
        return toPrint;
    }
}