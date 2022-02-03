package node_based;

import node_based.exceptions.DuplicateElementException;
import node_based.exceptions.ElementNotFoundException;
import node_based.exceptions.NoParentException;
import node_based.nodes.BNode;

public class BinaryTree<DataType> {
    //attributes
    private BNode root;
    private String str;
    private int size;

    //constructor
    public BinaryTree() {
        this.size = 0;
    }

    //getters and setters
    public int getSize() {
        return this.size;
    }

    //methods
    public void add(DataType data) throws DuplicateElementException {
        addUtil(data, this.root);
        this.size++;
    }

    private boolean compare(DataType data1, DataType data2) {
        return ((Integer) data1 > (Integer) data2);
    }

    private void addUtil(DataType data, BNode root) throws DuplicateElementException {
        if (root == null) {
            this.root = new BNode<DataType>(data);
        }
        else if (compare(data, (DataType) root.getData())) {
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
            throw new DuplicateElementException();
        }
    }

    //find an element in the tree
    public BNode findNodeByData(DataType data) throws ElementNotFoundException {
        return findNodeByDataUtil(data, this.root);
    }

    private BNode findNodeByDataUtil(DataType data, BNode root) throws ElementNotFoundException {
        if (root == null) {
            throw new ElementNotFoundException();
        }
        else if (root.getData() == data) {
            return root;
        }
        else if (compare(data, (DataType) root.getData())) {
            return findNodeByDataUtil(data, root.getRight());
        }
        else {
            return findNodeByDataUtil(data, root.getLeft());
        }
    }

    // returns the parent of a specified node
    private BNode getParent(BNode node, BNode root) throws NoParentException {
        if (root == node) {
            throw new NoParentException();
        }
        else if ((root.getLeft() == node) || (this.root.getRight() == node)) {
            return root;
        }
        else if (compare((DataType) node.getData(), (DataType) root.getData())) {
            return  getParent(node, root.getRight());
        }
        else {
            return  getParent(node, root.getLeft());
        }
    }

    // delete an element - work in progress
    // do not worry, it will not throw exception
    public void deleteNode(BNode node) throws NoParentException {
        try {
            BNode parent = getParent(node, this.root);
            if ((node.getLeft() == null) && (node.getRight() == null)) {
                if (parent.getLeft() == root) {
                    parent.setLeft(null);
                }
                else {
                    parent.setRight(null);
                }
            }
            else {
                BNode successor = node.getLeft();
                while (successor.getRight() != null) {
                    successor = successor.getRight();
                }
                BNode sucessorParent = getParent(successor, this.root);
                if (successor.getLeft() != null) {
                    sucessorParent.setRight(successor.getLeft());
                }
                else {
                    sucessorParent.setRight(null);
                }
                successor.setLeft(node.getLeft());
                successor.setRight(node.getRight());
                if (parent.getLeft() == node) {
                    parent.setLeft(successor);
                }
                else {
                    parent.setRight(successor);
                }
                this.root = successor;
            }
        } catch (NoParentException e) {
            // we know this is the case when we are deleting the node
            BNode successor = this.root.getLeft();
            while (successor.getRight() != null) {
                successor = successor.getRight();
            }
            BNode sucessorParent = getParent(successor, this.root);
            if (successor.getLeft() != null) {
                sucessorParent.setRight(successor.getLeft());
            }
            else {
                sucessorParent.setRight(null);
            }
            successor.setLeft(root.getLeft());
            successor.setRight(root.getRight());
            this.root.setRight(null);
            this.root.setLeft(null);
            this.root = successor;
        }
    }

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
