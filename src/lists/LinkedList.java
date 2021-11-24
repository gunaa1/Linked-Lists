package lists;

public class LinkedList<DataType> extends List<DataType> {
    //methods
    public DataType get(int index) {
        if (index >= super.nodes) return null;
        return (DataType) getNodeFromHead(index).getData();
    }
}