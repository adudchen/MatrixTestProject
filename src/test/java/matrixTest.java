import org.junit.Assert;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.stream.Stream;

public class matrixTest
{
    @Test
    public void multiplyOnNumTest1()
    {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMat = {{5, 10, 15}, {20, 25, 30}, {35, 40, 45}};
        Assert.assertArrayEquals(Matrix.multiplyOnNum(matrix, 5), expectedMat);
    }

    @Test
    public void multiplyOnNumTest2()
    {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMat = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        Assert.assertArrayEquals(Matrix.multiplyOnNum(matrix, 0), expectedMat);
    }

    @Test
    public void multiplyOnNumTest3()
    {
        double[][] matrix = {};
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiplyOnNum(matrix, 4));
    }

    @Test
    public void randMatrixTest1()
    {
        double[][] firstMatrix = Matrix.randomMatrix(5, 5);
        Assert.assertEquals(firstMatrix.length, 5);
        Assert.assertEquals(firstMatrix[0].length, 5);
    }

    @Test
    public void randMatrixTest2()
    {
        assertThrows(IllegalArgumentException.class, () -> Matrix.randomMatrix(0, 5));
    }

    @Test
    public void randMatrixTest3()
    {
        assertThrows(IllegalArgumentException.class, () -> Matrix.randomMatrix(5, -1));
    }

    @Test
    public void matrixSumTest1()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMatrix = Matrix.multiplyOnNum(firstMatrix, 2);
        Assert.assertArrayEquals(expectedMatrix, Matrix.sum(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixSumTest2()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}};
        assertThrows(IllegalArgumentException.class, () -> Matrix.sum(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixMultiplyTest1()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{0, 1, 2}};
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixMultiplyTest2()
    {
        double[][] firstMatrix = Matrix.randomMatrix(100, 100);
        double[][] secondMatrix = Matrix.randomMatrix(2, 100);
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixMultiplyTest3()
    {
        double[][] firstMatrix = Matrix.randomMatrix(30, 100);
        double[][] secondMatrix = Matrix.randomMatrix(100, 50);
        Matrix.multiply(firstMatrix, secondMatrix);
    }

    @Test
    public void matrixDiffTest()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}};
        assertThrows(IllegalArgumentException.class, () -> Matrix.diff(firstMatrix, secondMatrix));
    }

    @ParameterizedTest
    @MethodSource("paramsForMultiply")
    public void paramMultiplyTest(double[][] firstMatrix, double[][] secondMatrix, double[][] expectedMatrix) {
        Assert.assertArrayEquals(expectedMatrix, Matrix.multiply(firstMatrix, secondMatrix));
    }

    @ParameterizedTest
    @MethodSource("paramsForDiff")
    public void paramDiffTest(double[][] firstMatrix, double[][] secondMatrix, double[][] expectedMatrix) {
        Assert.assertArrayEquals(expectedMatrix, Matrix.diff(firstMatrix, secondMatrix));
    }

    private static Stream<Arguments> paramsForMultiply() {
        return Stream.of(
                Arguments.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][] {{0}, {1}, {2}},
                        new double[][] {{8}, {17}, {26}}),
                Arguments.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}},
                        new double[][] {{24, 30, 36}, {51, 66, 81}, {78, 102, 126}}));
    }

    private static Stream<Arguments> paramsForDiff() {
        return Stream.of(
                Arguments.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}),
                Arguments.of(new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}},
                        new double[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    @Test
    public void readFromFileTest() throws IOException {
        double [][] firstMatrix = Matrix.readFromFile("resources/matrixFile.txt");
        double [][] expMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertArrayEquals(expMatrix, firstMatrix);
    }
}