package array_based;

public class QuickFind extends UnionFind {
    // Contructor
    public QuickFind(int numOfElements) {
        super(numOfElements);
    }

    
    // ADT Methods
    public void union(int a, int b) {
        int idA = super.id[a];
        int idB = super.id[b];
        for (int index = 0; index < super.id.length; index++) {
            if (super.id[index] == idB) super.id[index] = idA;
        }
    }
    
    public boolean connected(int a, int b) {
        return (super.id[a] == super.id[b]);
    }
    
}
