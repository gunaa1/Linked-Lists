package array_based;


// Imports
import exceptions.InvalidSizeException;
import exceptions.QueueOverflowException;
import exceptions.ReductionRefactorException;


public class Queue {
    // Attributes
    private int topIndex = 0;
    private int endIndex = 0;
    private int maxSize = 0;
    private int size = 0;
    private int[] arr;

    // Constructor
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }


    // ADT Methods
    public void add(int newElement) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }
        arr[this.endIndex] = newElement;
        this.endIndex = (this.endIndex + 1) % this.maxSize;
        this.size++;
    }

    public int pop() {
        int returnInd = this.topIndex;
        this.topIndex = (this.topIndex + 1) % this.maxSize;
        this.size--;
        return arr[returnInd];
    }

    public int peek() {
        return arr[this.topIndex];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private boolean isFull() {
        return (size == maxSize);
    }

    public int size() {
        return this.size;
    }

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
