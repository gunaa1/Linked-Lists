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


    // Getter
    public int getSize() {
        return this.size;
    }


    // ADT Methods
    public void add(int newElement) throws StackOverflowError {
        if (isFull()) {
            throw new StackOverflowError("Stack is full!");
        }
        else {
            this.arr[this.topIndex++] = newElement;
            size++;
        }
    }

    public int pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        this.topIndex--;
        this.size--;
        return this.arr[this.topIndex];
    }

    public int peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        return this.arr[this.topIndex - 1];
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public boolean isFull() {
        return (this.size == this.maxSize);
    }


    // Default Methods
    public String toString() {
        String toPrint = "Stack: (Bottom) ";
        for (int num: arr) {
            toPrint += num + " ";
        }
        toPrint += "(Top)";
        return toPrint;
    }
}
