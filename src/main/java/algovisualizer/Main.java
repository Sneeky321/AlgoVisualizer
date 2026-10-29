package algovisualizer;

import algovisualizer.ui.VisualizerFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualizerFrame frame = new VisualizerFrame();
            frame.setVisible(true);
        });
    }
}
