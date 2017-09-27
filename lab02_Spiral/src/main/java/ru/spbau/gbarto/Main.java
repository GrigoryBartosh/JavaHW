package ru.spbau.gbarto;

public class Main {
    /**
     * Launches the common test for matrix 3x3
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] data = {{9,8,7},
                        {4,5,6},
                        {3,2,1}};
        Matrix m = new Matrix(data);
        System.out.println("Source matrix: ");
        m.print();
        m.sort();
        System.out.println("Sorted matrix: ");
        m.print();
        System.out.println("Spiral: ");
        m.spiral();
    }
}
