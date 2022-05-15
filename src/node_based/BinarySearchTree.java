package node_based;


// Imports
import exceptions.DuplicateElementException;
import exceptions.ElementNotFoundException;
import node_based.nodes.BNode;


public class BinarySearchTree<DataType> {
    // Attributes
    private BNode root;
    private int size;
    private boolean sucessorReplacement;


    // Constructors
    public BinarySearchTree() {
        this.size = 0;
        this.sucessorReplacement = true;
    }

    public BinarySearchTree(boolean sucessorReplacement) {
        this.size = 0;
        this.sucessorReplacement = sucessorReplacement;
    }

    public BinarySearchTree(DataType[] initalArr) {
        this.size = 0;
        this.sucessorReplacement = true;
        try {
            for (DataType element : initalArr) {
                this.add(element);
            }
        } catch (DuplicateElementException error) {
            System.out.println("There was an issue adding initial elements to the BST.");
        }
    }

    public BinarySearchTree(DataType[] initalArr, boolean sucessorReplacement) {
        this.size = 0;
        this.sucessorReplacement = sucessorReplacement;
        try {
            for (DataType element : initalArr) {
                this.add(element);
            }
        } catch (DuplicateElementException error) {
            System.out.println("There was an issue adding initial elements to the BST.");
        }
    }

    
    // Helper
    private BNode getSucessor(BNode node) {
        node = node.getRight();
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private BNode getPredecessor(BNode node) {
        node = node.getRight();
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getRight();
        }
        return node;
    }

    private void swapData(BNode node1, BNode node2) {
        Object data1 = node1.getData();
        node1.setData(node2.getData());
        node2.setData(data1);
    }


    // ADT Methods
    public void add(DataType data) throws DuplicateElementException {
        this.root = addUtil(this.root, data);
        this.size++;
    }

    private BNode addUtil(BNode root, DataType data) throws DuplicateElementException {
        if (root == null) {
            root = new BNode(data);
        }
        else {
            if ((Integer) data > (Integer) root.getData())
                root.setRight(addUtil(root.getRight(), data));
            else if ((Integer) data < (Integer) root.getData())
                    root.setLeft(addUtil(root.getLeft(), data));
            else
                throw new DuplicateElementException();
        }
        return root;
    }

    public void delete(DataType data) throws ElementNotFoundException {
        this.root = deleteUtil(this.root, data);
        this.size--;
    }

    private BNode deleteUtil(BNode root, DataType data) {
        if ((Integer) data > (Integer) root.getData()) {
            root.setRight(deleteUtil(root.getRight(), data));
        }
        else if ((Integer) data < (Integer) root.getData()) {
            root.setLeft(deleteUtil(root.getLeft(), data));
        }
        else {
            if ((root.getLeft() == null) && (root.getRight() == null)) {
                root = null;
            }
            else if ((root.getLeft() != null) && (root.getRight() != null)) {
                if (sucessorReplacement) {
                    BNode replacement = getSucessor(root);
                    swapData(root, replacement);
                    root.setRight(deleteUtil(root.getRight(), data));
                }
                else {
                    BNode replacement = getPredecessor(root);
                    swapData(root, replacement);
                    root.setLeft(deleteUtil(root.getLeft(), data));
                }
            }
            else {
                BNode child = (root.getLeft() == null) ? root.getRight(): root.getLeft();
                root = child;
            }
        }

        return root;
    }

    public boolean isEmpty() {
        if (this.size == 0)
            return true;
        return false;
    }

    public BNode search(DataType data) throws ElementNotFoundException {
        BNode curNode = this.root;
        while (curNode != null) {
            if ((Integer) data > (Integer) curNode.getData())
                curNode = curNode.getRight();
            else if ((Integer) data < (Integer) curNode.getData())
                curNode = curNode.getLeft();
            else
                return curNode;
        }
        throw new ElementNotFoundException();
    }

    // Traversal Techniques
    public void inOrderTraversal() {
        System.out.print("Binary Search Tree: ");
        inOrderTraversalUtil(this.root);
        System.out.println();
    }

    private void inOrderTraversalUtil(BNode curNode) {
        if (curNode != null) {
            inOrderTraversalUtil(curNode.getLeft());
            System.out.print(curNode.getData() + " ");
            inOrderTraversalUtil(curNode.getRight());
        }
    }

    public void inReverseOrderTraversal() {
        System.out.print("Binary Search Tree: ");
        inReverseOrderTraversalUtil(this.root);
    }

    private void inReverseOrderTraversalUtil(BNode curNode) {
        if (curNode != null) {
            inReverseOrderTraversalUtil(curNode.getRight());
            System.out.println(curNode.getData() + " ");
            inReverseOrderTraversalUtil(curNode.getLeft());
        }
    }

    public void inPreorder() {
        System.out.print("Binary Search Tree: ");
        inPreorderUtil(this.root);
    }

    private void inPreorderUtil(BNode curNode) {
        if (curNode != null) {
            System.out.println(curNode.getData() + " ");
            inPreorderUtil(curNode.getLeft());
            inPreorderUtil(curNode.getRight());
        }
    }

    public void inPostorder() {
        System.out.print("Binary Search Tree: ");
        inPostorderUtil(this.root);
    }

    private void inPostorderUtil(BNode curNode) {
        if (curNode != null) {
            inPostorderUtil(curNode.getLeft());
            inPostorderUtil(curNode.getRight());
            System.out.println(curNode.getData() + " ");
        }
    }

    public void levelByLevel() {
        String toPrint = "Binary Search Tree: ";
        Queue q = new Queue<BNode>();
        q.add(this.root);
        while (!q.isEmpty()) {
            BNode curNode = (BNode) q.poll();
            if (curNode.getLeft() != null) q.add(curNode.getLeft());
            if (curNode.getRight() != null) q.add(curNode.getRight());
            toPrint += curNode.getData() + " ";
        }
        System.out.println(toPrint);
    }
}