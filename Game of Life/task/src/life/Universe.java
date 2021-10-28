package life;

import java.util.Random;


public class Universe {

    private Random random;
    private int size;
    private char[][] matrix;
    private int generations;

    public Universe(int size, int generation, long seed) {
        this.size = size;
        this.matrix = new char[size][size];
        this.generations = generation;
        this.random = new Random(seed);
        setStartUniverse();
    }

    public char[][] getMatrix() {
        return matrix.clone();
    }

    private void setStartUniverse() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextBoolean() ? 'O' : ' ';
            }
        }
    }

    public int countGens() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(matrix[i][j] == 'O') {
                    count++;
                }
            }
        }
        return count;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(matrix[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
