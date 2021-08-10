public class Stack<DataType> {
    //attributes
    private Node head;
    private int length;

    //contructors
    public Stack() {
        head = new Node(null);
        length = 0;
    }

    //methods
    public void add(DataType object) {
        Node newNode = new Node(object);
        getNode(length - 1).setLink(newNode);
        length++;
    }

    public DataType pop() {
        DataType data = (DataType) getNode(length - 1).getData();
        getNode(--length - 1).setLink(null);
        return data;
    }

    public DataType peek() {
        return (DataType) getNode(length - 1).getData();
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
        String string = "Stack: ";
        for (int index = 0; index < length; index++) {
            node = node.getLink();
            string += (node.getData() + " ");
        }
        return string;
    }
}
