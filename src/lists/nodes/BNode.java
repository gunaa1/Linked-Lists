package lists.nodes;

public class BNode<DataType> {
    //attributes
    private DataType data;
    private BNode left, right;

    //contructor
    public BNode(DataType data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    //getters & setters
    public BNode getLeft() {
        return this.left;
    }

    public BNode getRight() {
        return this.right;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public void setRight(BNode right) {
        this.right = right;
    }

    public DataType getData() {
        return this.data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
