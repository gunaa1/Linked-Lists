package array_based;


// imports
import node_based.Stack;
import utility.Utility;
import node_based.Queue;
import java.util.ArrayList;
import exceptions.ElementNotFoundException;


public class Graph {
    // Attributes
    private ArrayList<ArrayList<Integer>> adjList;
    private int[][] adjMatrix;

    // Contructors
    public Graph() {
        this.adjMatrix = new int[0][0];
    }

    public Graph(int[][] initialGraph) {
        if (isValidAdjMatrix(initialGraph)) {
            this.adjMatrix = initialGraph;
        }
        else {
            this.adjMatrix = new int[0][0];
        }
        matrixToList(this.adjMatrix);
    }

    public Graph(ArrayList<ArrayList<Integer>> initialList) {
        this.adjList = initialList;
        listToMatrix(initialList);
    }


    // Getters
    public ArrayList<ArrayList<Integer>> getAdjList() {
        return this.adjList;
    }

    public int[][] getAdjMatrix() {
        return this.getAdjMatrix();
    }


    // Setters
    public void setGraph(ArrayList<ArrayList<Integer>> newAdjList) {
        this.adjList = newAdjList;
        listToMatrix(newAdjList);
    }

    public void setGraph(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
        matrixToList(adjMatrix);
    }
    

    // Helper
    private void matrixToList(int[][] matrix) {
        ArrayList<ArrayList<Integer>> newAdjList = new ArrayList<ArrayList<Integer>>();
        for (int row = 0; row < matrix.length; row++) {
            ArrayList<Integer> node = new ArrayList<>();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != 0)
                    node.add(col);
            }
            newAdjList.add(node);
        }
        this.adjList = newAdjList;
    }

    private void listToMatrix(ArrayList<ArrayList<Integer>> adjList) {
        int size = adjList.size();
        int[][] newAdjMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                newAdjMatrix[i][adjList.get(i).get(j)] = 1;
            }
        }
        this.adjMatrix = newAdjMatrix;
    }

    private boolean isValidAdjMatrix(int[][] adjMatrix) {
        for (int row = 0; row < adjMatrix.length; row++) {
            if (adjMatrix.length != adjMatrix[row].length) return false;
        }
        return true;
    }

    private int getOutDegree(int nodeNum) throws ElementNotFoundException {
        if (nodeNum >= adjMatrix.length)
            throw new ElementNotFoundException();
        return this.adjList.get(nodeNum).size();    
    }

    private int getInDegree(int nodeNum) throws ElementNotFoundException {
        if (nodeNum >= adjMatrix.length)
            throw new ElementNotFoundException();
        int inDegree = 0;
        for (int index = 0; index < adjMatrix.length; index++) {
            if (index == nodeNum) continue;
            for (int element: this.adjMatrix[index])
                if (element == nodeNum) inDegree++;
        }
        return inDegree;
    }

    private boolean isUndirected() {
        int size = this.adjMatrix.length - 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < (size - row); col++) {
                if (adjMatrix[row][col] != adjMatrix[col][row]) return false;
            }
        }
        return true;
    }

    private boolean isSimpleGraph() {
        for (int i = 0; i < this.adjMatrix.length; i++)
            if (this.adjMatrix[i][i] != 0) return false;
        return true;
    }


    // ADT Methods
    public int numOfWaysToWithLength(int node1, int node2, int k) {
        int[][] result = this.adjMatrix;
        for (int i = 0; i < k; i++)
            result = Utility.matrixMult(result, result);
        return result[node1][node2];
    }

    public void addVertexUndirected(int[] connections) {
        ArrayList<Integer> newVertex = new ArrayList<Integer>();
        for (int vertexConnection: connections) {
            newVertex.add(vertexConnection);
        }
        this.adjList.add(newVertex);
        listToMatrix(this.adjList);
    }

    // direction = true means in degree and = false means out degree
    public void addVertexDirected(int[] connections, boolean[] direction) {
        ArrayList<Integer> newVertex = new ArrayList<Integer>();
        for (int index = 0; index < connections.length; index++) {
            if (direction[index])
                this.adjList.get(connections[index]).add(this.adjList.size());
            else
                newVertex.add(connections[index]);
        }
        this.adjList.add(newVertex);
        listToMatrix(this.adjList);
    }

    public void removeVertex(int vertex) {
        this.adjMatrix = removeVertexUtil(this.adjMatrix, vertex);
        matrixToList(this.adjMatrix);
    }

    private int[][] removeVertexUtil(int[][] matrix, int vertex) {
        int[][] newMatrix = new int[matrix.length - 1][matrix.length - 1];
        for (int row = 0, addRow = 0; row < matrix.length; addRow++, row++) {
            for (int col = 0, addCol = 0; col < matrix.length; addCol++, col++) {
                if (row == vertex) {
                    addRow--;
                    col = matrix.length;
                }
                else if (col == vertex) addCol--;
                else newMatrix[addRow][addCol] = matrix[row][col];
            }
        }
        return newMatrix;
    }

    public boolean isStronglyConnected() {
        boolean[] visited = DFS(0, this.adjList.size());
        for (boolean visitedThisNode: visited) {
            if (!visitedThisNode) return false;
        }

        int[][] newAdjMatrix = invertEdges(this.adjMatrix);
        matrixToList(newAdjMatrix);

        visited = DFS(0, this.adjList.size());
        for (boolean visitedThisNode: visited) {
            if (!visitedThisNode) {
                matrixToList(this.adjMatrix);
                return false;
            }
        }
        matrixToList(this.adjMatrix);
        return true;
    }

    private int[][] invertEdges(int[][] adjMatrix) {
        int size = this.adjMatrix.length - 1;
        int[][] newAdjMatrix = new int[size + 1][size + 1];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++)
                newAdjMatrix[row][col] = adjMatrix[col][row];
        }
        return newAdjMatrix;
    }

    public boolean[] DFS(int initV, int findV) {
        if (initV == findV) {
            System.out.println("Currently at the vertex being searched!");
            return null;
        }
        
        boolean[] visited = new boolean[this.adjList.size()];
        Stack<Integer> path = new Stack<Integer>();
        path.add(initV);
        DFSUtil(initV, findV, visited, path);

        System.out.println("DFS path: " + path);
        return visited;
    }

    private void DFSUtil(int initV, int findV, boolean[] visited, Stack<Integer> pathStack) {
        if (pathStack.peek() == findV) return;
        visited[initV] = true;
        pathStack.add(initV);
        for (int index = 0; index < adjList.get(initV).size(); index++) {
            if (!visited[adjList.get(initV).get(index)])
                DFSUtil(adjList.get(initV).get(index), findV, visited, pathStack);
        }
    }

    public void BFS(int initV, int findV) {
        int[] edges = new int[this.adjList.size()];
        for (int index = 1; index < this.adjList.size(); index++)
            edges[index] = -1;

        Queue<Integer> q = new Queue<Integer>();
        q.add(initV);
        while(!q.isEmpty()) {
            for (int edgeVal: this.adjList.get(q.peek())) {
                if (edges[edgeVal] == -1) {
                    edges[edgeVal] = q.poll();
                    if (edgeVal == findV) break;
                    q.add(edgeVal);
                }
            }
        }

        printBFSPath(edges, findV);
    }

    private void printBFSPath(int[] edges, int findV) {
        int traverseInd = findV;
        String path = "";
        while (traverseInd != 0) {
            path += (traverseInd + " ,");
            traverseInd = edges[traverseInd];
        }

        String reverseStr = "0";
        for(int charInd = path.length() - 1; charInd >= 0; charInd--) {
            reverseStr += path.charAt(charInd);
        }
        System.out.println("BFS Path: " + reverseStr);
    }

    public int[][] findTransitiveClosure() {
        int size = this.adjMatrix.length;
        int[][] k = Utility.getCopy(this.adjMatrix);

        for (int bridge = 0; bridge < size; bridge++) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if ((k[row][col] == 1) || ((k[row][bridge] == 1) && (k[bridge][col] == 1))) {
                        k[row][col] = 1;
                    }
                }
            }
        }

        return k;
    }

    public int[] topologicalSort() {
        int[] order = new int[this.adjMatrix.length];
        int[][] temp = Utility.getCopy(this.adjMatrix);
        int orderIndex = 0;
        for (int index = 0; index < temp.length; index++) {
            if (temp[index][0] == -1) continue;
            int sum = 0;
            for (int i: temp[index]) sum += i;
            if (sum == 0) {
                temp[index][0] = -1;
                for (int row = 0; row < temp.length; row++) {
                    if(row == index) continue;
                    if (temp[row][index] == -1) continue;
                    temp[row][index] = 0;
                }
                order[orderIndex++] = index;
                index = -1;
            }
        }
        return order;
    }


    // Default Method
    public String toString() {
        matrixToList(this.adjMatrix);
        String toPrint = "Graph: \n";
        for (int row = 0; row < adjList.size(); row++) {
            toPrint += "Node: " + row + " | ";
            for (int col = 0; col < adjList.get(row).size(); col++) {
                toPrint += this.adjList.get(row).get(col) + ", ";
            }
            toPrint += "\n";
        }
        return toPrint;
    }
}