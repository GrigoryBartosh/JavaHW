package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The class Matrix can print a matrix in spiral order and sort columns by the first elements.
 */
public class Matrix {
    final static private int[] DX = {1, 0, -1, 0};
    final static private int[] DY = {0, -1, 0, 1};

    private int matrix[][];
    private int size;

    /**
     * Copies and transposes the matrix using two for cycles.
     *
     * @param matrix input matrix
     */
    public Matrix(@NotNull int[][] matrix) throws IllegalArgumentException{
        size = matrix.length;
        if (matrix.length % 2 != 1)
        {
            throw new IllegalArgumentException("Incorrect size");
        }

        for (int i = 0; i < size; i++)
        {
            if (matrix[i].length != size) {
                throw new IllegalArgumentException("Incorrect size");
            }
        }

        this.matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.matrix[i][j] = matrix[j][i];
            }
        }
    }

    /**
     * Sorts the rows by the first element. In the source matrix, these are the columns.
     */
    public void sort() {
        Arrays.sort(matrix, Comparator.comparingInt(o -> o[0]));
    }

    /**
     * Prints the matrix in spiral order.
     */
    public void spiral() {
        int x = size / 2;
        int y = size / 2;
        int direction = 0;
        int step = 1;
        boolean flagChange = false;
        for (int i = 0; i < size * size;) {
            for (int j = 0; j < step; j++) {
                System.out.print(matrix[y][x] + " ");
                x += DX[direction];
                y += DY[direction];
            }
            i += step;
            direction = (direction + 1) % 4;
            if (flagChange) {
                step++;
            }
            flagChange ^= true;
        }
    }

    /**
     * Prints the matrix in usual order.
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }
}
