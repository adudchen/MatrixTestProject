import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Matrix {

    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix[0].length != secondMatrix.length) {
            throw new IllegalArgumentException("Number of columns of firstMatrix and number of rows of secondMatrix are not equal");
        }

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }

    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static void print(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public static double[][] sum(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            throw new IllegalArgumentException("Matrix is not same size");
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            if (firstMatrix[i].length != secondMatrix[i].length)
                throw new IllegalArgumentException("Matrix is not same size");
        }

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = firstMatrix[row][col] + secondMatrix[row][col];
            }
        }
        return result;
    }

    public static double[][] diff(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            throw new IllegalArgumentException("Matrix is not same size");
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            if (firstMatrix[i].length != secondMatrix[i].length)
                throw new IllegalArgumentException("Matrix is not same size");
        }

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = firstMatrix[row][col] - secondMatrix[row][col];
            }
        }
        return result;
    }

    public static double[][] multiplyOnNum(double[][] matrix, double num) {
        if (matrix.length == 0)
            throw new IllegalArgumentException("Matrix is empty");

        double[][] result = new double[matrix.length][matrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = matrix[row][col] * num;
            }
        }
        return result;
    }

    public static double[][] randomMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Matrix sizes are negative");
        }

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = Math.random() * 10;
            }
        }
        return result;
    }

    public static double[][] readFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        int m = lines.get(0).split(" ").length;
        int n = lines.size();
        double[][] matrix = new double[n][m];
        for(int i = 0; i < n; i++){
            String [] splittedLine = lines.get(i).split(" ");
            for (int j = 0; j < splittedLine.length; j++) {
                matrix[i][j] = Double.parseDouble(splittedLine[j]);
            }
        }
        return matrix;
    }
}