package array_based;

import java.util.EmptyStackException;

public class Stack {
    // attributes
    private int topIndex = 0;
    private int maxSize = 0;
    private int size = 0;
    private int[] arr;

    // constructor
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public void add(int newElement) throws StackOverflowError {
        if (isFull()) {
            throw new StackOverflowError("Stack is full!");
        }
        else {
            this.arr[this.topIndex++] = newElement;
        }
    }

    public int pop() throws EmptyStackException {
        if (--this.topIndex < 0) {
            throw new EmptyStackException();
        }
        return this.arr[this.topIndex];
    }

    public int peek() {
        try {
            return this.arr[--this.topIndex];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Stack is empty");
            return Integer.MIN_VALUE;
        }
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public boolean isFull() {
        return (this.size == this.maxSize);
    }
}
