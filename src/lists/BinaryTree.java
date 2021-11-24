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

    //printing out elements in the binary tree from least to greatest
    public void inOrderTraversal() {
        inOrderTraversalUtil(root);
    }

    private void inOrderTraversalUtil(BNode node) {
        if (node != null) {
            inOrderTraversalUtil(node.getLeft());
            System.out.println(node.getData() + " ");
            inOrderTraversalUtil(node.getRight());
        }
    }

    //printing out the element in the binary tree from greatest to least
    public void inReverseOrderTraversal() {
        inReverseOrderTraversalUtil(root);
    }

    private void inReverseOrderTraversalUtil(BNode node) {
        if (node != null) {
            inReverseOrderTraversalUtil(node.getRight());
            System.out.println(node.getData() + " ");
            inReverseOrderTraversalUtil(node.getLeft());
        }
    }

    //printing the elements in the binary tree in the order left - right - current
    public void inPreorder() {
        inPreorderUtil(root);
    }

    private void inPreorderUtil(BNode node) {
        if (node != null) {
            System.out.println(node.getData() + " ");
            inPreorderUtil(node.getLeft());
            inPreorderUtil(node.getRight());
        }
    }

    //printing the elements in the binary tree in the order current - left - right
    public void inPostorder() {
        inPostorderUtil(root);
    }

    private void inPostorderUtil(BNode node) {
        if (node != null) {
            inPostorderUtil(node.getLeft());
            inPostorderUtil(node.getRight());
            System.out.println(node.getData() + " ");
        }
    }

    //default print method
    public void print() {
        inOrderTraversalUtil(root);
    }
}
