package lists;

public class Node<DataType> {
    //attributes
    private DataType data;
    private Node link;

    //constructor
    public Node (DataType data) {
        this.data = data;
        this.link = null;
    }

    //setters and getters
    public Node getLink() {
        return this.link;
    }

    public void setLink(Node node) {
        this.link = node;
    }

    public DataType getData() {
        return this.data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
