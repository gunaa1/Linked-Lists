package lists;

public class BinaryTree<Type> {
    //attributes
    BNode root;

    //constructor
    public BinaryTree(Type data) {
        root = new BNode<Type>(data);
    }

    //methods
    public void add(Type data) {
        try {
            addUtil(data, root);
        } catch (AdditionException error) {
            System.out.println("Duplicate Element found!");
        }
    }

    private BNode addUtil(Type data, BNode node) throws AdditionException {
        if (node == null) {
            node = new BNode<Type>(data);
        }
        else if ((Integer) data > (Integer) node.getData()) {
            node.setRight(addUtil(data, node.getRight()));
        }
        else if ((Integer) data < (Integer) node.getData()) {
            node.setLeft(addUtil(data, node.getLeft()));
        }
        else {
            AdditionException error = new AdditionException("Duplicate Element");
            throw error;
        }
        return node;
    }

    public void inOrderTraversal() {
        inOrderTraversalUtil(root);
    }

    private void inOrderTraversalUtil(BNode node) {
        if (node.getLeft() != null) {
            inOrderTraversalUtil(node.getLeft());
        }
        if (node != null) {
            System.out.println(node.getData());
        }
        if (node.getRight() != null) {
            inOrderTraversalUtil(node.getRight());
        }
    }
}
