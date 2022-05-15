package array_based;

import exceptions.NegativeWeightCycleException;

import java.util.ArrayList;

public class WeightedGraph {
    // attributes
    private ArrayList<ArrayList<Integer>> adjList;
    private int[][] adjMatrix;

    //contructor
    public WeightedGraph() {
        this.adjMatrix = new int[0][0];
    }

    public WeightedGraph(int[][] initialGraph) {
        if (isValidAdjMatrix(initialGraph)) {
            this.adjMatrix = initialGraph;
        }
        else {
            this.adjMatrix = new int[0][0];
        }
        matrixToList(this.adjMatrix);
    }

    // helper methods
    private void matrixToList(int[][] matrix) {
        ArrayList<ArrayList<Integer>> newAdjList = new ArrayList<ArrayList<Integer>>();
        for (int row = 0; row < matrix.length; row++) {
            ArrayList<Integer> node = new ArrayList<>();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != 0) {
                    node.add(col);
                }
            }
            newAdjList.add(node);
        }
        this.adjList = newAdjList;
    }

    private boolean isValidAdjMatrix(int[][] adjMatrix) {
        for (int[] row: adjMatrix)
            if (adjMatrix.length != row.length) return false;
        return true;
    }

    private int nextVertexToVisit(boolean[] visited, int[] distances) {
        int leastDistance = Integer.MAX_VALUE;
        int nextVertexToVisit = -1;
        for (int index = 0; index < distances.length; index++) {
            if (!visited[index]) {
                if (distances[index] < leastDistance) {
                    leastDistance = distances[index];
                    nextVertexToVisit = index;
                }
            }
        }
        return nextVertexToVisit;
    }

    // ADT
    public int[][] bellmanFord(int startV, int endV) throws NegativeWeightCycleException {
        int numOfVertices = this.adjMatrix.length;
        int[] prevVertex = new int[numOfVertices];
        int[] distances = new int[numOfVertices];
        for (int index = 1; index < numOfVertices; index++) {
            distances[index] = Integer.MAX_VALUE;
        }
        distances[startV] = 0;

        for (int runTimes = 0; runTimes < numOfVertices; runTimes++) {
            for (int curVertex = 0; curVertex < numOfVertices; curVertex++) {
                for (int index = 0; index < this.adjList.get(curVertex).size(); index++) {
                    int visitingVertex = this.adjList.get(curVertex).get(index);
                    int newDist = distances[curVertex] + this.adjMatrix[curVertex][visitingVertex];
                    if (newDist < distances[visitingVertex]) {
                        distances[visitingVertex] = newDist;
                        prevVertex[visitingVertex] = curVertex;
                    }
                }
            }
        }

        for (int fromV = 0; fromV < numOfVertices; fromV++) {
            for (int index = 0; index < this.adjList.get(fromV).size(); index++) {
                int visitingVertex = this.adjList.get(fromV).get(index);
                int newDist = distances[fromV] + this.adjMatrix[fromV][visitingVertex];
                System.out.println(newDist);
                if (newDist < distances[visitingVertex]) {
                    System.out.println("FromV: " + fromV + "\t EndV: " + visitingVertex);
                    throw new NegativeWeightCycleException();
                }
            }
        }

        int[][] returnVals = {distances, prevVertex};
        return returnVals;
    }

    public int[][] dijkstraShortestPath(int startV, int endV) {
        int numOfVertices = this.adjMatrix.length;
        boolean[] visited = new boolean[numOfVertices];
        int[] prevVertex = new int[numOfVertices];
        int[] distances = new int[numOfVertices];
        for (int index = 1; index < numOfVertices; index++) {
            distances[index] = Integer.MAX_VALUE;
        }
        distances[startV] = 0;

        for (int numOfVerticesVisited = 0; numOfVerticesVisited < numOfVertices; numOfVerticesVisited++) {
            int curVertex = nextVertexToVisit(visited, distances);
            if (curVertex != -1) {
                for (int index = 0; index < this.adjList.get(curVertex).size(); index++) {
                    int vistingVertex = this.adjList.get(curVertex).get(index);
                    if (!visited[vistingVertex]) {
                        int newDist = distances[curVertex] + this.adjMatrix[curVertex][vistingVertex];
                        if (newDist < distances[vistingVertex]) {
                            distances[vistingVertex] = newDist;
                            prevVertex[vistingVertex] = curVertex;
                        }
                    }
                }
                visited[curVertex] = true;
            }
        }

        int[][] returnVals = {distances, prevVertex};
        return returnVals;
    }

    public int[][] allPairsAlgorithm() {
        int[][] temp = getModCopy(this.adjMatrix);
        int numOfVertices = this.adjMatrix.length;

        for (int bride = 0; bride < numOfVertices; bride++) {
            for (int row = 0; row < numOfVertices; row++) {
                for (int col = 0; col < numOfVertices; col++) {
                    if (temp[row][bride] != 0 && temp[bride][col] != 0) {
                        if (temp[row][col] == 0) {
                            temp[row][col] = (temp[row][bride] + temp[bride][col]);
                        }
                        else {
                            temp[row][col] = Math.min(temp[row][col], (temp[row][bride] + temp[bride][col]));
                        }
                    }
                }
            }
        }

        return temp;
    }

    public int[] topologicalSort() {
        int[] order = new int[this.adjMatrix.length];
        int[][] temp = getModCopy(this.adjMatrix);
        int orderIndex = 0;
        for (int index = 0; index < temp.length; index++) {
            if (temp[index][0] == Integer.MIN_VALUE) continue;
            int sum = 0;
            for (int i: temp[index]) sum += i;
            if (sum == 0) {
                temp[index][0] = Integer.MIN_VALUE;
                for (int row = 0; row < temp.length; row++) {
                    if(row == index) continue;
                    if (temp[row][index] == Integer.MIN_VALUE) continue;
                    temp[row][index] = 0;
                }
                order[orderIndex++] = index;
                index = -1;
            }
        }
        return order;
    }

    private int[][] getModCopy(int[][] matrixA) {
        int[][] newMatrix = new int[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[0].length; col++) {
                if (matrixA[row][col] == 0) newMatrix[row][col] = Integer.MAX_VALUE;
                else newMatrix[row][col] = matrixA[row][col];
            }
        }
        return newMatrix;
    }

    private void print1DMatrix(int[] matrix, String matrixName) {
        System.out.println(matrixName + ": ");
        for(int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + ", ");
        }
        System.out.println();
        System.out.println();
    }

    private void print2DMatrix(int[][] matrix, String matrixName) {
        System.out.println(matrixName + ": ");
        for(int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // testing
    public static void main(String[] args) throws NegativeWeightCycleException {
        int[][] weightedGraph = {
                {0, -4, 0, 8, 0, 0, 0},
                {0, 0, 8, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 9},
                {0, 11, 0, 0, -1, -7, 0},
                {0, 0, 0, 0, 0, 0, 10},
                {0, 0, 2, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        WeightedGraph wg =  new WeightedGraph(weightedGraph);
        wg.print1DMatrix(wg.topologicalSort(), "Topological Order");
        wg.print2DMatrix(wg.dijkstraShortestPath(0, 6), "Dijkstra: Shortest Distances, Previous Vertices");
        wg.print2DMatrix(wg.bellmanFord(0, 6), "Bellman Ford: Shortest Distances, Previous Vertices");
        wg.print2DMatrix(wg.allPairsAlgorithm(), "All Pairs Algorithm");
    }
}
