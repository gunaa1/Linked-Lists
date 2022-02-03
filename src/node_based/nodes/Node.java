package node_based.nodes;

public abstract class Node<DataType> {
    //attributes
    protected DataType data;

    public Node(DataType data) {
        this.data = data;
    }

    //getters and setters
    public DataType getData() {
        return this.data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
