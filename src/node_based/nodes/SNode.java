package node_based.nodes;

public class SNode<DataType> extends Node<DataType> {
    //attributes
    protected SNode nextLink;

    //constructor
    public SNode (DataType data) {
        super(data);
        this.nextLink = null;
    }

    //setters and getters
    public SNode getNextLink() {
        return this.nextLink;
    }

    public void setNextLink(SNode node) {
        this.nextLink = node;
    }
}