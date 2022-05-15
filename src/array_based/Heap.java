package array_based;

import exceptions.NoElementsException;
import exceptions.ReductionRefactorException;

public abstract class Heap implements HeapMethods {
    // attributes
    protected int[] heap;
    private int size;
    protected int addToIndex;

    // contructor
    public void Heap() {
        this.size = 0;
        this.addToIndex = 0;
    }

    public void Heap(int[] initialArr) {
        this.size = initialArr.length;
        this.addToIndex = 0;
        int[] arr = new int[size];
        try {
            for (int num : initialArr) {
                this.add(num);
            }
        } catch (ReductionRefactorException error) {
            System.out.println("Unexpected issue while refactoring the array.");
        }
    }

    // base methods
    protected int getLeftChildIndex(int parentIndex) {
        return (2* parentIndex + 1);
    }

    protected int getRightChildIndex(int parentIndex) {
        return (2* parentIndex + 2);
    }

    protected int getParent(int childIndex) {
        return ((childIndex - 1) / 2);
    }

    private void doubleSize() {
        if (size == 0) {
            size = 1;
        }
        else {
            size *= 2;
        }
        int[] newHeap = new int[size];
        for (int index = 0; index < (size / 2); index++) {
            newHeap[index] = heap[index];
        }

        heap = newHeap;
    }

    private void halfSize() throws ReductionRefactorException {
        if ((addToIndex - 1) > (size / 2)) {
            throw new ReductionRefactorException();
        }

        int[] newHeap = new int[size / 2];
        for (int index = 0; index < size / 2; index++) {
            newHeap[index] = heap[index];
        }

        size /= 2;
        heap = newHeap;
    }

    protected void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // ADT methods
    public void add(int num) throws ReductionRefactorException {
        if (addToIndex >= size) {
            doubleSize();
        }

        heap[addToIndex] = num;
        bubbleUp();

        addToIndex++;
    }

    public int pop() throws NoElementsException, ReductionRefactorException {
        if (isEmpty()) {
            throw new NoElementsException();
        }

        int toRemove = heap[0];
        swap(0, --addToIndex);
        bubbleDown();

        if (addToIndex < (size / 2)) {
            halfSize();
        }

        return toRemove;
    }

    public int peek() {
        return heap[0];
    }

    public boolean isEmpty() {
        if (addToIndex == 0) {
            return true;
        }
        return false;
    }

    // default methods
    public String toString() {
        String toPrint=  "Heap: ";
        for (int index = 0; index < addToIndex; index++) {
            toPrint += heap[index] + "\t";
        }
        toPrint += "\n";
        return toPrint;
    }
}