package array_based;


public class QuickUnion extends UnionFind {
    // Contructor
    public QuickUnion(int numOfElements) {
        super(numOfElements);
    }


    // Helper
    public int findRoot(int a) {
        while (super.id[a] != a) {
            super.id[a] = super.id[id[a]];
            a = super.id[a];
        }
        return a;
    }

    
    // ADT Methods
    public void union(int a, int b) {
        if (findRoot(a) != findRoot(b)) {
            id[b] = id[id[a]];
            numConnectedComponents--;
        }
    }

    public boolean connected(int a, int b) {
        return (findRoot(a) == findRoot(b)) ? true: false;
    }
}
