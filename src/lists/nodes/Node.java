package lists.nodes;

public class Node<DataType> {
    //attributes
    private DataType data;
    private Node nextLink, prevLink;

    //constructor
    public Node (DataType data) {
        this.data = data;
        this.nextLink = null;
        this.prevLink = null;
    }

    //setters and getters
    public Node getNextLink() {
        return this.nextLink;
    }

    public Node getPrevLink() {
        return this.prevLink;
    }

    public void setNextLink(Node node) {
        this.nextLink = node;
    }

    public void setPrevLink(Node node) {
        this.prevLink = node;
    }

    public DataType getData() {
        return this.data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
