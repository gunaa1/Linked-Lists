package lists;

import java.lang.Thread;

public class Main {
    public static void main (String[] args) {
        //can test any code here:
        // Queue<Integer> q = new Queue<Integer>();
        // Integer num = new Integer(99);
        // Integer num1 = new Integer(92);
        // q.add(num);
        // q.add(num1);
        // System.out.println(q);
        // System.out.println(q.poll());
        // System.out.println(q);

        Thread t = Thread.currentThread();

        BinaryTree testTree = new BinaryTree<Integer>(3);
        testTree.add(1); testTree.add(134); testTree.add(9);
        testTree.inOrderTraversal();
    }
}