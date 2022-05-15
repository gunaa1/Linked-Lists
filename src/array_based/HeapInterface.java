package array_based;

public interface HeapInterface {
    // implements
    void bubbleUp(int curInd);
    void bubbleDown(int curInd);
    int swapIndex(int parentIndex);
    void update(int index);
}