package lists;

import lists.exceptions.AdditionException;
import lists.nodes.BNode;

public class BinaryTree<DataType> {
    //attributes
    private BNode root;
    private String str;
    private int nodes;

    //constructor
    public BinaryTree(DataType data) {
        root = new BNode<DataType>(data);
    }

    //getters and setters
    public int getSize() {
        return this.nodes;
    }

    //methods
    public void add(DataType data) {
        try {
            addUtil(data, this.root);
            this.nodes++;
        } catch (AdditionException error) {
            System.out.println("Duplicate Element found!");
        }
    }

    private void addUtil(DataType data, BNode root) throws AdditionException {
        if (root == null) {
            this.root = new BNode<DataType>(data);
        }
        else if ((Integer) data > (Integer) root.getData()) {
            if (root.getRight() == null) {
                root.setRight(new BNode<DataType>(data));
            }
            else {
                addUtil(data, root.getRight());
            }
        }
        else if ((Integer) data < (Integer) root.getData()) {
            if (root.getLeft() == null) {
                root.setLeft(new BNode<DataType>(data));
            }
            else {
                addUtil(data, root.getLeft());
            }
        }
        else {
            AdditionException error = new AdditionException("Duplicate Element");
            throw error;
        }
    }

    //find an element in the tree
    public BNode findByElement(DataType data) {
        return findByElementUtil(data, this.root);
    }

    private BNode findByElementUtil(DataType data, BNode root) {
        if (root == null) {
            return null;
        }
        else if (root.getData() == data) {
            return root;
        }
        else if ((Integer) data > (Integer) root.getData()) {
            return findByElementUtil(data, root.getRight());
        }
        else {
            return findByElementUtil(data, root.getLeft());
        }
    }

    //delete an element - work in progress
    // public BNode deleteNode(BNode node) {
    //     if (node.getLeft() == null && node.getRight() == null) {

    //     }
    // }


    //printing out elements in the binary tree from least to greatest
    public void inOrderTraversal() {
        str = "";
        inOrderTraversalUtil(root);
        System.out.println(str);
    }

    private void inOrderTraversalUtil(BNode node) {
        if (node != null) {
            inOrderTraversalUtil(node.getLeft());
            str += node.getData() + " ";
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
    public String toString() {
        str = "";
        inOrderTraversalUtil(root);
        return str;
    }
}
