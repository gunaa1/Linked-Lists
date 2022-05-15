package array_based;

public class MaxHeap extends Heap {
    @Override
    public void bubbleUp(int curInd) {
        while (curInd > 0) {
            int parentInd = getParent(curInd);
            if (heap[curInd] > heap[parentInd]) {
                swap(curInd, parentInd);
            }
            else {
                break;
            }
            curInd = parentInd;
        }
    }

    @Override
    public void bubbleDown(int curInd) {
        while (curInd < addToIndex) {
            int greaterInd = swapIndex(curInd);
            if (greaterInd == -1) {
                break;
            }
            if (heap[curInd] < heap[greaterInd]) {
                swap(curInd, greaterInd);
            }
            else {
                break;
            }
            curInd = greaterInd;
        }
    }

    @Override
    public int swapIndex(int parentIndex) {
        int leftChildIndex = getLeftChildIndex(parentIndex);
        int rightChildIndex = getRightChildIndex(parentIndex);
        if ((rightChildIndex >= addToIndex) && (leftChildIndex >= addToIndex)) {
            return -1;
        }
        else if (rightChildIndex >= addToIndex) {
            return leftChildIndex;
        }
        else if (leftChildIndex >= addToIndex) {
            return rightChildIndex;
        }
        else {
            if (heap[leftChildIndex] >= heap[rightChildIndex]) {
                return leftChildIndex;
            }
            else {
                return rightChildIndex;
            }
        }
    }
}
