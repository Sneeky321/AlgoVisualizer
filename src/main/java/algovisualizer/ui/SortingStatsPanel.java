
package algovisualizer.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SortingStatsPanel extends JPanel {
    private final JLabel timeLabel;
    private final JLabel compareLabel;
    private final JLabel swapLabel;
    private final JLabel writeLabel;

    private static final Color BACKGROUND = new Color(40, 50, 60);
    private static final Color SECONDARY_COLOR = new Color(155, 170, 190);
    private static final Color TEXT_COLOR = new Color(225, 230, 240);

    public SortingStatsPanel() {
        setLayout(new GridLayout(1, 4, 12, 0));
        setBackground(new Color(30, 40, 50));
        setBorder(new EmptyBorder(10, 15, 15, 15));

        timeLabel = createValueLabel("0.000 ms");
        compareLabel = createValueLabel("0");
        swapLabel = createValueLabel("0");
        writeLabel = createValueLabel("0");

        add(createStatCard("TIME", timeLabel, Color.BLUE));
        add(createStatCard("COMPARISONS", compareLabel, Color.YELLOW));
        add(createStatCard("SWAPS", swapLabel, Color.RED));
        add(createStatCard("WRITES", writeLabel, Color.GREEN));
    }

    private JLabel createValueLabel(String value) {
        JLabel label = new JLabel(value);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(TEXT_COLOR);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JPanel createStatCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(BACKGROUND);
        card.setBorder(new EmptyBorder(12, 10, 10, 15));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        titleLabel.setForeground(SECONDARY_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel accent = new JPanel();
        accent.setBackground(accentColor);
        accent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        accent.setPreferredSize(new Dimension(100, 3));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(valueLabel);
        card.add(Box.createVerticalGlue());
        card.add(Box.createVerticalStrut(8));
        card.add(accent);

        return card;
    }

    public void updateStats(long timeNs, int comparisons, int swaps, int writes) {
        double timeMs = timeNs / 1_000_000.0;

        timeLabel.setText(String.format("%.3f ms", timeMs));
        compareLabel.setText(String.valueOf(comparisons));
        swapLabel.setText(String.valueOf(swaps));
        writeLabel.setText(String.valueOf(writes));
    }

    public void resetStats() {
        timeLabel.setText("0.000 ms");
        compareLabel.setText("0");
        swapLabel.setText("0");
        writeLabel.setText("0");
    }
}
