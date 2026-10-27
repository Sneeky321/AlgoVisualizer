package algovisualizer;

import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++){
            array[i] = rand.nextInt(101);
        }
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Algorithm Visualizer");
            SortingPanel sortingPanel = new SortingPanel(array);

            frame.add(sortingPanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }
}
