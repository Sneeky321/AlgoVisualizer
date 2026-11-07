package algovisualizer.ui;

import algovisualizer.sorting.BubbleSort;
import algovisualizer.sorting.InsertionSort;
import algovisualizer.sorting.SelectionSort;

import javax.swing.*;
import java.awt.*;

public class VisualizerFrame extends JFrame {
    private final SortingPanel sortingPanel;
    private final SortAnimator animator;
    private final JComboBox<String> algorithmSelector;

    public VisualizerFrame(){
        super("Algorithm Visualizer");

        sortingPanel = new SortingPanel(10);
        SortingStatsPanel statsPanel = new SortingStatsPanel();
        animator = new SortAnimator(sortingPanel, statsPanel, new SelectionSort());

        algorithmSelector = new JComboBox<>(new String[]{
                "Bubble Sort",
                "Insertion Sort",
                "Selection Sort"
        });

        JSlider delaySlider = new JSlider(0, 200, 50);
        delaySlider.setMajorTickSpacing(50);
        delaySlider.setMinorTickSpacing(10);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);

        JButton generateButton = new JButton("Generate");
        JButton sortButton = new JButton("Sort");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(algorithmSelector);

        JPanel delayPanel = new JPanel(new BorderLayout());
        delayPanel.add(new JLabel("Animation delay (ms):"), BorderLayout.NORTH);
        delayPanel.add(delaySlider, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(statsPanel, BorderLayout.SOUTH);
        bottomPanel.add(delayPanel, BorderLayout.CENTER);

        delaySlider.addChangeListener(event ->{
            animator.setDelay(delaySlider.getValue());
        });

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

        algorithmSelector.addActionListener(event -> {
            if(!animator.isRunning()){
                String selected = (String) algorithmSelector.getSelectedItem();

                switch(selected){
                    case "Bubble Sort" ->
                        animator.setAlgorithm(new BubbleSort());
                    case "Insertion Sort" ->
                        animator.setAlgorithm(new InsertionSort());
                    case "Selection Sort" ->
                        animator.setAlgorithm(new SelectionSort());
                }
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
