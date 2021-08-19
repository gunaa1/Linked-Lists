package lists;

public class LinkedList<DataType> {
    //attributes
    private Node head;
    private int length;

    public LinkedList() {
        head = new Node(null);
        length = 0;
    }

    //methods
    public void add(DataType object) {
        Node newNode = new Node(object);
        getNode(length - 1).setLink(newNode);
        length++;
    }

    public void displayList() {
        Node latest = head;
        while (latest.getLink() != null) {
            latest = latest.getLink();
            System.out.println(latest.getData());
        }
    }

    public int getLength() {
        return this.length;
    }

    private Node getNode(int index) {
        Node latest = head;
        if (index < length) {
            for (int i = 0; i <= index; i++) {
                latest = latest.getLink();
            }
            return latest;
        }
        return null;
    }

    public DataType get(int index) {
        return (DataType) getNode(index).getData();
    }
}