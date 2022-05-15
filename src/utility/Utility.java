package utility;

public class Utility {
     public static int[][] getCopy(int[][] matrixA) {
        int[][] newMatrix = new int[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[0].length; col++) {
                newMatrix[row][col] = matrixA[row][col];
            }
        }
        return newMatrix;
    }

    public static void print1DMatrix(int[] matrix, String matrixName) {
        System.out.println(matrixName + ": ");
        for(int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + ", ");
        }
    }

    public static void print2DMatrix(int[][] matrix, String matrixName) {
        System.out.println(matrixName + ": ");
        for(int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + ", ");
            }
            System.out.println();
        }
    }

    public static int[][] matrixMult(int[][] firstMatrix, int[][] secondMatrix) {
        assert(firstMatrix[0].length == secondMatrix.length);
        int[][] result = new int[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                for (int i = 0; i < secondMatrix.length; i++) {
                    result[row][col] += firstMatrix[row][i] * secondMatrix[i][col];
                }
            }
        }
        return result;
    }

    public static int[][] getStartingDistances(int[][] matrixA) {
        int[][] newMatrix = new int[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[0].length; col++) {
                if (matrixA[row][col] == 0) newMatrix[row][col] = Integer.MAX_VALUE;
                else newMatrix[row][col] = matrixA[row][col];
            }
        }
        return newMatrix;
    }
}
