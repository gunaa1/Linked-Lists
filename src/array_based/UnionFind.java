package array_based;

public class UnionFind {
    // attributes
    private int[] id;
    private int numConnectedComponents;

    // contructor
    public UnionFind(int numOfElements) {
        numConnectedComponents = 0;
        id = new int[numOfElements];
        for (int index = 0; index < numOfElements; index++) {
            id[index] = index;
        }
    }

    // helper
    private int findRoot(int a) {
        while (id[a] != a) {
            id[a] = id[id[a]];
            a = id[a];
        }
        return a;
    }

    // ADT
    public void union(int a, int b) {
        id[b] = id[id[a]];
        numConnectedComponents--;
    }

    public int getNumOfConnectedComponents() {
        return this.numConnectedComponents;
    }

    public boolean connected(int a, int b) {
        return (findRoot(a) == findRoot(b)) ? true: false;
    }
}