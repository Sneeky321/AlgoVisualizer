package algovisualizer.ui;

import javax.swing.*;
import java.awt.*;

public class VisualizerFrame extends JFrame {
    private final SortingPanel sortingPanel;
    private final SortAnimator animator;

    public VisualizerFrame(){
        super("Algorithm Visualizer");

        sortingPanel = new SortingPanel(10);
        SortingStatsPanel statsPanel = new SortingStatsPanel();
        animator = new SortAnimator(sortingPanel, statsPanel);

        JButton generateButton = new JButton("Generate");
        JButton sortButton = new JButton("Sort");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(sortButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(statsPanel, BorderLayout.SOUTH);

        generateButton.addActionListener(event -> {
            if (!animator.isRunning()) {
                sortingPanel.generateArray(10);
                statsPanel.resetStats();
            }
        });

        sortButton.addActionListener(event -> {
            if (!animator.isRunning()) {
                animator.start();
            }
        });

        setLayout(new BorderLayout());
        add(sortingPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
