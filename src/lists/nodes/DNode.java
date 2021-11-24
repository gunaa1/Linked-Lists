package lists.nodes;

public class DNode<DataType> extends SNode {
    //attributes
    private DNode prevLink;

    //constructor
    public DNode (DataType data) {
        super(data);
        this.prevLink = null;
    }

    //setters and getters
    public DNode getPrevLink() {
        return this.prevLink;
    }

    public void setPrevLink(DNode node) {
        this.prevLink = node;
    }
}
