package algovisualizer.ui;

import algovisualizer.sorting.BubbleSort;
import algovisualizer.sorting.InsertionSort;
import algovisualizer.sorting.MergeSort;
import algovisualizer.sorting.SelectionSort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VisualizerFrame extends JFrame {
    private final SortingPanel sortingPanel;
    private final SortAnimator animator;
    private final JComboBox<String> algorithmSelector;

    private static final Color BACKGROUND = new Color(28, 37, 52);
    private static final Color PANEL_BACKGROUND = new Color(36, 47, 64);
    private static final Color TEXT_COLOR = new Color(225, 232, 242);

    public VisualizerFrame(){
        super("Algorithm Visualizer");

        sortingPanel = new SortingPanel(10);
        SortingStatsPanel statsPanel = new SortingStatsPanel();
        animator = new SortAnimator(sortingPanel, statsPanel, new BubbleSort());

        algorithmSelector = new JComboBox<>(new String[]{
                "Bubble Sort",
                "Insertion Sort",
                "Selection Sort",
                "Merge Sort"
        });

        JPanel topPanel = createTopPanel();
        JPanel bottomPanel = createBottomPanel(statsPanel);

        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND);

        add(topPanel, BorderLayout.NORTH);
        add(sortingPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND);
        topPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        styleComboBox();

        JPanel selectorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        selectorPanel.setBackground(BACKGROUND);

        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setForeground(TEXT_COLOR);
        algorithmLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        selectorPanel.add(algorithmLabel);
        selectorPanel.add(algorithmSelector);

        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        legendPanel.setBackground(BACKGROUND);

        legendPanel.add(createLegendItem("Compare", Color.YELLOW));
        legendPanel.add(createLegendItem("Swap", Color.RED));
        legendPanel.add(createLegendItem("Write", Color.GREEN));

        topPanel.add(selectorPanel, BorderLayout.WEST);
        topPanel.add(legendPanel, BorderLayout.EAST);

        algorithmSelector.addActionListener(event -> {
            if (!animator.isRunning()) {
                String selected = (String) algorithmSelector.getSelectedItem();

                switch (selected) {
                    case "Bubble Sort" ->
                            animator.setAlgorithm(new BubbleSort());
                    case "Insertion Sort" ->
                            animator.setAlgorithm(new InsertionSort());
                    case "Selection Sort" ->
                            animator.setAlgorithm(new SelectionSort());
                    case "Merge Sort" ->
                            animator.setAlgorithm(new MergeSort());
                }
            }
        });

        return topPanel;
    }

    private JPanel createLegendItem(String text, Color color) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        item.setBackground(BACKGROUND);

        JPanel dot = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        };

        dot.setOpaque(false);
        dot.setPreferredSize(new Dimension(11, 11));

        JLabel label = new JLabel(text);
        label.setForeground(TEXT_COLOR);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        item.add(dot);
        item.add(label);

        return item;
    }

    private JPanel createBottomPanel(SortingStatsPanel statsPanel) {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(BACKGROUND);
        bottomPanel.setBorder(new EmptyBorder(5, 20, 15, 20));

        JButton generateButton = new JButton("Generate");
        JButton sortButton = new JButton("Sort");

        styleButton(generateButton, new Color(65, 85, 115));
        styleButton(sortButton, new Color(50, 139, 104));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        buttonPanel.setBackground(BACKGROUND);

        buttonPanel.add(generateButton);
        buttonPanel.add(sortButton);

        JSlider delaySlider = new JSlider(0, 200, 50);
        delaySlider.setMajorTickSpacing(50);
        delaySlider.setMinorTickSpacing(10);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setBackground(BACKGROUND);
        delaySlider.setForeground(TEXT_COLOR);

        JPanel delayPanel = new JPanel(new BorderLayout(0, 5));
        delayPanel.setBackground(BACKGROUND);
        delayPanel.setBorder(new EmptyBorder(5, 10, 10, 10));

        JLabel delayLabel = new JLabel("Animation delay: 50 ms");
        delayLabel.setForeground(TEXT_COLOR);
        delayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        delayPanel.add(delayLabel, BorderLayout.NORTH);
        delayPanel.add(delaySlider, BorderLayout.CENTER);

        delaySlider.addChangeListener(event -> {
            int delay = delaySlider.getValue();
            animator.setDelay(delay);
            delayLabel.setText("Animation delay: " + delay + " ms");
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

        bottomPanel.add(buttonPanel);
        bottomPanel.add(delayPanel);
        bottomPanel.add(statsPanel);

        return bottomPanel;
    }

    private void styleButton(JButton button, Color color){
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    private void styleComboBox() {
        algorithmSelector.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        algorithmSelector.setForeground(TEXT_COLOR);
        algorithmSelector.setBackground(PANEL_BACKGROUND);
        algorithmSelector.setFocusable(false);
    }
}
