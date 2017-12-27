package ru.spbau.gbarto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MatrixTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Redirects the output stream.
     */
    @Before
    public void setOutput() {
        System.setOut(new PrintStream(output));
    }

    /**
     * A not square matrix test.
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void badInput() throws Exception {
        Matrix m = new Matrix(new int[][]{  {9, 8, 7},
                                            {4, 5, 6}});
    }

    /**
     * A simple test for a matrix of one element.
     *
     * @throws Exception
     */
    @Test
    public void sort_1x1() throws Exception {
        Matrix m = new Matrix(new int[][]{{1}});
        m.sort();
        m.print();
        assertEquals("1", output.toString().trim());
    }

    /**
     * Sorts tree columns.
     *
     * @throws Exception
     */
    @Test
    public void sort_3x3() throws Exception {
        Matrix m = new Matrix(new int[][]{  {9, 8, 7},
                                            {4, 5, 6},
                                            {3, 2, 1}});
        m.sort();
        m.print();
        assertEquals("7 8 9 \n6 5 4 \n1 2 3", output.toString().trim());
    }

    /**
     * A simple test for a matrix of one element.
     *
     * @throws Exception
     */
    @Test
    public void spiral_1x1() throws Exception {
        Matrix m = new Matrix(new int[][]{{1}});
        m.spiral();
        assertEquals("1", output.toString().trim());
    }

    /**
     * Prints the matrix 3x3 in spiral order.
     *
     * @throws Exception
     */
    @Test
    public void spiral_3x3() throws Exception {
        Matrix m = new Matrix(new int[][]{  {1, 2, 3},
                                            {4, 5, 6},
                                            {7, 8, 9}});
        m.spiral();
        assertEquals("5 8 7 4 1 2 3 6 9", output.toString().trim());
    }


    /**
     * A simple test for a matrix of one element.
     *
     * @throws Exception
     */
    @Test
    public void print_1x1() throws Exception {
        Matrix m = new Matrix(new int[][]{{1}});
        m.print();
        assertEquals("1", output.toString().trim());
    }

    /**
     * Big test. It checks the output of matrix 5x5.
     *
     * @throws Exception
     */
    @Test
    public void print_5x5() throws Exception {
        Matrix m = new Matrix(new int[][]{  {8, 8, 0, 0, 7},
                                            {5, 5, 5, 3, 6},
                                            {5, 3, 5, 0, 5},
                                            {0, 0, 0, 0, 4},
                                            {3, 2, 1, 0, -1}});
        m.print();
        assertEquals("8 8 0 0 7 \n5 5 5 3 6 \n5 3 5 0 5 \n0 0 0 0 4 \n3 2 1 0 -1", output.toString().trim());
    }

    /**
     * Returns the output stream to the normal state.
     */
    @After
    public void cleanOutput() {
        System.setOut(null);
    }
}