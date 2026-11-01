package algovisualizer.ui;

import javax.swing.*;
import java.awt.*;

public class SortingStatsPanel extends JPanel {
    private final JLabel timeLabel;
    private final JLabel compareLabel;
    private final JLabel swapLabel;
    private final JLabel writeLabel;

    public SortingStatsPanel(){
        setLayout(new GridLayout(1, 4, 10, 0));

        timeLabel = new JLabel("Time: -", SwingConstants.CENTER);
        compareLabel = new JLabel("Comparisons: 0", SwingConstants.CENTER);
        swapLabel = new JLabel("Swaps: 0", SwingConstants.CENTER);
        writeLabel = new JLabel("Writes: 0", SwingConstants.CENTER);

        add(timeLabel);
        add(compareLabel);
        add(swapLabel);
        add(writeLabel);
    }

    public void updateStats(long timeNs, int comparisons,
                            int swaps, int writes){
        double timeMs = timeNs / 1_000_000.0;

        timeLabel.setText(String.format("Time: %.3fms", timeMs));
        compareLabel.setText("Comparisons: " + comparisons);
        swapLabel.setText("Swaps: " + swaps);
        writeLabel.setText("Writes: " + writes);
    }

    public void resetStats(){
        timeLabel.setText("Time: -");
        compareLabel.setText("Comparisons: 0");
        swapLabel.setText("Swaps: 0");
        writeLabel.setText("Writes: 0");
    }
}
