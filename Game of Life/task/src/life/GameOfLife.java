package life;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    private int gen = 0;
    private int alive = 0;
    private boolean hasStarted = false;
    private boolean isPlaying = false;
    private Universe universe = new Universe(20, Integer.MAX_VALUE, 20);

    private JLabel GenerationLabel = new JLabel();
    private JLabel AliveLabel = new JLabel();

    private JToggleButton PlayToggleButton = new JToggleButton("Play/Pause");
    private JButton ResetButton = new JButton("Reset");

    private JPanel stats = new JPanel();
    private Grid grid = new Grid();

    private ActionListener eventHandler = new Events();

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        GenerationLabel.setName("GenerationLabel");
        AliveLabel.setName("AliveLabel");
        PlayToggleButton.setName("PlayToggleButton");
        ResetButton.setName("ResetButton");
        setStats(0, 0);

        PlayToggleButton.addActionListener(eventHandler);
        ResetButton.addActionListener(eventHandler);

        stats.add(ResetButton);
        stats.add(PlayToggleButton);
        stats.add(GenerationLabel);
        stats.add(AliveLabel);
        stats.add(grid);
        add(stats);
        setVisible(true);
    }

    private void setStats(int generation, int alive) {
        this.gen = generation;
        this.alive = alive;
        this.GenerationLabel.setText("Generation #" + this.gen);
        this.AliveLabel.setText("Alive: " + this.alive);
    }

    private void run() {

        Thread newT = new Thread(() -> {
            int i = gen;
            while (isPlaying) {
                Generation generation = new Generation(universe.getMatrix());
                universe.setMatrix(generation.nextGeneration());
                this.gen = i + 1;
                this.alive = universe.countGens();
                this.setStats(gen, alive);
                this.refreshGrid(universe.getMatrix());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });


        newT.start();

    }

    private void reset() {
        this.universe = new Universe(20, Integer.MAX_VALUE, 20);
        gen = 0;
        alive = 0;
        setStats(0, 0);
        stats.remove(grid);
        this.grid = new Grid();
        stats.add(grid);
    }

    private void refreshGrid(char[][] universe) {

        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                if (universe[i][j] == 'O') {
                    grid.fillCell(i, j);
                } else {
                    grid.removeCell(i, j);
                }
            }
        }
    }

    private class Events implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == PlayToggleButton) {
                if (!hasStarted) {
                    hasStarted = true;
                    isPlaying = true;
                    run();
                } else {
                    isPlaying = !isPlaying;
                    if (isPlaying) {
                        run();
                    }
                }

            }

            if (e.getSource() == ResetButton) {
                isPlaying = false;
                reset();
            }
        }
    }

}