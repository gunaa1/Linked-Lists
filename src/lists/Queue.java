package lists;

public class Queue<DataType> {
    //attributes
    private Node head;
    private int length;

    //contructors
    public Queue() {
        head = new Node(null);
        length = 0;
    }

    //methods
    public void add(DataType object) {
        Node newNode = new Node(object);
        getNode(length - 1).setLink(newNode);
        length++;
    }

    public DataType poll() {
        DataType data = (DataType) getNode(0).getData();
        head.setLink(getNode(1));
        length--;
        return data;
    }

    public DataType peek() {
        return (DataType) getNode(0).getData();
    }

    private Node getNode(int index) {
        Node latest = head;
        if (index < length) {
            for (int i = 0; i <= index; i++) {
                latest = latest.getLink();
            }
        }
        return latest;
    }

    public String toString() {
        Node node = head;
        String string = "Queue: ";
        for (int index = 0; index < length; index++) {
            node = node.getLink();
            string += (node.getData() + " ");
        }
        return string;
    }
}
