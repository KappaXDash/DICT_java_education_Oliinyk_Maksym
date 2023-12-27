package MatrixProcessing;
import java.util.Scanner;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMatrices();
                    break;
                case 2:
                    multiplyByConstant();
                    break;
                case 3:
                    multiplyMatrices();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    calculateDeterminant();
                    break;
                case 6:
                    inverseMatrix();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void inverseMatrix() {
        int[][] matrix = readMatrix();

        if (isSquare(matrix)) {
            int determinant = calculateDeterminant(matrix);
            if (determinant != 0) {
                double[][] inverse = calculateInverse(matrix);
                System.out.println("The result is:");
                printMatrix(inverse);
            } else {
                System.out.println("This matrix doesn't have an inverse.");
            }
        } else {
            System.out.println("ERROR: Inverse can only be calculated for a square matrix.");
        }
    }

    private static double[][] calculateInverse(int[][] matrix) {
        int n = matrix.length;
        int[][] augmentedMatrix = new int[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
            augmentedMatrix[i][i + n] = 1;
        }

        augmentedMatrix = rowReduce(augmentedMatrix);

        double[][] inverse = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = (double) augmentedMatrix[i][j + n];
            }
        }

        return inverse;
    }

    private static int[][] rowReduce(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        for (int i = 0; i < rowCount; i++) {
            // Find pivot for this row
            int pivotColumn = -1;
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] != 0) {
                    pivotColumn = j;
                    break;
                }
            }

            if (pivotColumn != -1) {
                // Make the pivot equal to 1
                int pivotValue = matrix[i][pivotColumn];
                for (int j = 0; j < colCount; j++) {
                    matrix[i][j] /= pivotValue;
                }

                // Eliminate other rows
                for (int k = 0; k < rowCount; k++) {
                    if (k != i) {
                        int factor = matrix[k][pivotColumn];
                        for (int j = 0; j < colCount; j++) {
                            matrix[k][j] -= factor * matrix[i][j];
                        }
                    }
                }
            }
        }

        return matrix;
    }

    private static void calculateDeterminant() {
        int[][] matrix = readMatrix();

        if (isSquare(matrix)) {
            int determinant = calculateDeterminant(matrix);
            System.out.println("The result is: " + determinant);
        } else {
            System.out.println("ERROR: Determinant can only be calculated for a square matrix.");
        }
    }

    private static int calculateDeterminant(int[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int determinant = 0;
        for (int i = 0; i < n; i++) {
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(getSubmatrix(matrix, 0, i));
        }

        return determinant;
    }

    private static int[][] getSubmatrix(int[][] matrix, int rowToRemove, int colToRemove) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;

        int[][] submatrix = new int[rows][cols];

        for (int i = 0, newRow = 0; i < matrix.length; i++) {
            if (i == rowToRemove) {
                continue;
            }

            for (int j = 0, newCol = 0; j < matrix[i].length; j++) {
                if (j == colToRemove) {
                    continue;
                }

                submatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }

            newRow++;
        }

        return submatrix;
    }

    private static boolean isSquare(int[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    private static int[][] readMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter the matrix values:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static void addMatrices() {
        // Код для додавання матриць, який ви вже реалізували на попередніх етапах
    }

    private static void multiplyByConstant() {
        // Код для множення матриці на константу, який ви вже реалізували на попередніх етапах
    }

    private static void multiplyMatrices() {
        // Код для множення матриць, який ви вже реалізували на попередніх етапах
    }

    private static void transposeMatrix() {
        // Код для транспонування матриці, який ви вже реалізували на попередніх етапах
    }
}