package array_based;


public abstract class UnionFind implements UnionFindInterface {
    // Attributes
    protected int[] id;
    protected int numConnectedComponents;


    // Contructor
    public UnionFind(int numOfElements) {
        numConnectedComponents = numOfElements;
        id = new int[numOfElements];
        for (int index = 0; index < numOfElements; index++) {
            id[index] = index;
        }
    }

    
    // ADT Methodss
    public int getNumOfConnectedComponents() {
        return this.numConnectedComponents;
    }
}