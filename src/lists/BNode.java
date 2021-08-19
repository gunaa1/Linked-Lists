package lists;

public class BNode<Type> {
    //attributes
    private BNode left; BNode right;
    private Type data;

    //contructor
    public BNode(Type data) {
        this.data = data;
    }

    //accessors & mutators
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

    public Type getData () {
        return this.data;
    }

    public void setData(Type data) {
        this.data = data;
    }
}
