package node_based;


// Imports
import node_based.DoublyLinkedList;


public class Tree<DataType extends Comparable<DataType>> {
    private class TreeNode<DataType extends Comparable<DataType>> implements Comparable<TreeNode<DataType>> {
        // Attributes
        private DataType data;
        private DoublyLinkedList<TreeNode<DataType>> links;

        // Constructor
        public TreeNode(DataType data) {
            this.data = data;
        }

        // Getter
        public DataType getData() {
            return this.data;
        }

        // ADT Methods
        public void addChild(DataType childData) {
            TreeNode<DataType> newNode = new TreeNode<>(childData);
            this.links.sortedAdd(newNode);
        }

        // Default method
        public int compareTo(TreeNode<DataType> otherNode) {
            return (this.data.compareTo(otherNode.getData()));
        }
    }

    // attributes
    private TreeNode root;


    // Contructor
    public Tree(DataType data) {
        TreeNode<DataType> newNode = new TreeNode<DataType>(data);
        this.root = newNode;
    }

    // ADT Methods
    public void add(TreeNode<DataType> addToNode, DataType childData) {
        addToNode.addChild(childData);
    }
}
