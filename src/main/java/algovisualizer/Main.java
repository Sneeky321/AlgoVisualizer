package algovisualizer;

import algovisualizer.ui.MainFrame;
import algovisualizer.ui.sorting.SortingView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
