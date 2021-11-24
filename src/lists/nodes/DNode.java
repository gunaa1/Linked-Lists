package lists.nodes;

public class DNode<DataType> {
    //attributes
    private DataType data;
    private DNode nextLink, prevLink;

    //constructor
    public DNode (DataType data) {
        this.data = data;
        this.nextLink = null;
        this.prevLink = null;
    }

    //setters and getters
    public DNode getNextLink() {
        return this.nextLink;
    }

    public DNode getPrevLink() {
        return this.prevLink;
    }

    public void setNextLink(DNode node) {
        this.nextLink = node;
    }

    public void setPrevLink(DNode node) {
        this.prevLink = node;
    }

    public DataType getData() {
        return this.data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
