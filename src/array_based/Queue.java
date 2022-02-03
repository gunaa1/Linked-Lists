package array_based;

import array_based.exceptions.InvalidSizeException;
import array_based.exceptions.QueueOverflowException;
import array_based.exceptions.ReductionRefactorException;

public class Queue {
    // attributes
    private int topIndex = 0;
    private int endIndex = 0;
    private int maxSize = 0;
    private int size = 0;
    private int[] arr;

    // constructor
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // add a new item to queue
    public void add(int newElement) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }
        arr[this.endIndex] = newElement;
        this.endIndex = (this.endIndex + 1) % this.maxSize;
        this.size++;
    }

    // popping an item from the queue
    public int pop() {
        int returnInd = this.topIndex;
        this.topIndex = (this.topIndex + 1) % this.maxSize;
        this.size--;
        return arr[returnInd];
    }

    // peeking at an item from the queue
    public int peek() {
        return arr[this.topIndex];
    }

    // checking if empty
    public boolean isEmpty() {
        return (size == 0);
    }

    // private method to check if full
    private boolean isFull() {
        return (size == maxSize);
    }

    // returns the size of the queue
    public int size() {
        return this.size;
    }

    // if given a valid refactor size, this function creates a new array of that size and moves all elements to new array
    public void setNewMaxSize(int newMaxSize) throws ReductionRefactorException, InvalidSizeException {
        if (newMaxSize < 0) {
            throw new InvalidSizeException();
        }
        else if (newMaxSize != this.maxSize) {
            if (newMaxSize < size) {
                throw new ReductionRefactorException();
            }
            else {
                int[] newArr = new int[newMaxSize];
                for (int index = topIndex, index2 = 0; index < size; index = (index + 1) % this.maxSize, index2++) {
                    newArr[index2] = arr[index];
                }
                this.arr = newArr;
                this.maxSize = newMaxSize;
                this.topIndex = 0;
                this.endIndex = size;
            }
        }
    }
}
