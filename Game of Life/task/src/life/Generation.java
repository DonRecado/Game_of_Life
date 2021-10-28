package life;

import java.util.ArrayList;

public class Generation {
    private char[][] universe;


    public Generation(char[][] startUniverse) {
        this.universe = startUniverse;
    }

    private ArrayList<Integer> counter() {

        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        int length = universe.length - 1;

        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {

                //e
                if (universe[i][j + 1 <= length ? j + 1 : 0] == 'O') {
                    count++;
                }
                //w
                if (universe[i][j - 1 >= 0 ? j - 1 : length] == 'O') {
                    count++;
                }
                //n
                if (universe[i - 1 >= 0 ? i - 1 : length][j] == 'O') {
                    count++;
                }
                //s
                if (universe[i + 1 <= length ? i + 1 : 0][j] == 'O') {
                    count++;
                }
                //nw
                if (universe[i - 1 >= 0 ? i - 1 : length][j - 1 >= 0 ? j - 1 : length] == 'O') {
                    count++;
                }
                //ne
                if (universe[i - 1 >= 0 ? i - 1 : length][j + 1 <= length ? j + 1 : 0] == 'O') {
                    count++;
                }
                //sw
                if (universe[i + 1 <= length ? i + 1 : 0][j - 1 >= 0 ? j - 1 : length] == 'O') {
                    count++;
                }
                //se
                if (universe[i + 1 <= length ? i + 1 : 0][j + 1 <= length ? j + 1 : 0] == 'O') {
                    count++;
                }

                list.add(count);
                count = 0;
            }

        }

        return list;

    }

    private void nextGeneration(ArrayList<Integer> list) {

        int indexList = -1;

        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                indexList++;
                if (universe[i][j] == 'O' && list.get(indexList).equals(3) || universe[i][j] == 'O' && list.get(indexList).equals(2)) {
                    universe[i][j] = 'O';
                } else if (universe[i][j] == ' ' && list.get(indexList).equals(3)) {
                    universe[i][j] = 'O';
                } else {
                    universe[i][j] = ' ';
                }
            }
        }

    }

    public char[][] nextGeneration() {
            ArrayList<Integer> count = counter();
            nextGeneration(count);
            return this.universe;
    }





}
