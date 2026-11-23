package algovisualizer.ui.searching;

import javax.swing.*;
import java.awt.*;

public class SearchStatsPanel extends JPanel {
    private JLabel comparisonsLabel;
    private JLabel timeLabel;
    private JLabel resultLabel;

    private static final Color BACKGROUND = new Color(30, 40, 50);
    private static final Color TEXT_COLOR = new Color(225, 230, 240);

    public SearchStatsPanel(){
        setBackground(BACKGROUND);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        comparisonsLabel = createLabel("Comparisons: 0");
        timeLabel = createLabel("Time: 0 ms");
        resultLabel = createLabel("Result: -");

        add(comparisonsLabel);
        add(timeLabel);
        add(resultLabel);
    }

    private JLabel createLabel(String text){
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_COLOR);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    public void updateStats(int comparisons, double time, int result){
        comparisonsLabel.setText("Comparisons: " + comparisons);
        timeLabel.setText(String.format("Time: %.3f ms", time));
        resultLabel.setText("Result: " + (result == -1 ? "Not found" : result));
    }

    public void resetStats(){
        comparisonsLabel.setText("Comparisons: 0");
        timeLabel.setText("Time: 0 ms");
        resultLabel.setText("Result: -");
    }
}
