
package algovisualizer.ui.searching;

import algovisualizer.searching.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SearchView extends JPanel {

    private final Runnable onBack;
    private final SearchPanel searchPanel;
    private final SearchAnimator animator;
    private final SearchStatsPanel statsPanel;
    private final JComboBox<String> algorithmSelector;
    private final JComboBox<Integer> sizeSelector;
    private final JTextField targetField;

    private JButton generateButton;

    private static final Color BACKGROUND = new Color(30, 40, 50);
    private static final Color PANEL_BACKGROUND = new Color(40, 50, 60);
    private static final Color TEXT_COLOR = new Color(225, 230, 240);

    public SearchView(Runnable onBack) {
        this.onBack = onBack;

        searchPanel = new SearchPanel(10);
        statsPanel = new SearchStatsPanel();
        animator = new SearchAnimator(searchPanel);

        animator.setDelay(50);
        animator.setOnAnimationFinished(() -> generateButton.setEnabled(true));

        algorithmSelector = new JComboBox<>(new String[]{
                "Linear Search",
                "Binary Search"
        });

        sizeSelector = new JComboBox<>(new Integer[]{10, 20, 30, 40, 50});

        targetField = new JTextField(6);

        JPanel topPanel = createTopPanel();
        JPanel bottomPanel = createBottomPanel();

        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        add(topPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND);
        topPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JButton backButton = new JButton("Back");
        styleButton(backButton, new Color(65, 85, 115));

        backButton.addActionListener(e -> onBack.run());

        styleComboBox();

        JPanel selectorPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0)
        );
        selectorPanel.setBackground(BACKGROUND);

        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setForeground(TEXT_COLOR);
        algorithmLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        selectorPanel.add(backButton);
        selectorPanel.add(algorithmLabel);
        selectorPanel.add(algorithmSelector);

        JPanel legendPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 15, 0)
        );
        legendPanel.setBackground(BACKGROUND);

        legendPanel.add(createLegendItem("Check", Color.YELLOW));
        legendPanel.add(createLegendItem("Found", Color.GREEN));
        legendPanel.add(createLegendItem("Unvisited", Color.BLUE));

        topPanel.add(selectorPanel, BorderLayout.WEST);
        topPanel.add(legendPanel, BorderLayout.EAST);

        return topPanel;
    }

    private JPanel createLegendItem(String text, Color color) {
        JPanel item = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 6, 0)
        );
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

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(BACKGROUND);
        bottomPanel.setBorder(new EmptyBorder(5, 20, 15, 20));

        generateButton = new JButton("Generate");

        JButton searchButton = new JButton("Search");

        styleButton(generateButton, new Color(65, 85, 115));
        styleButton(searchButton, new Color(50, 140, 105));

        JPanel inputPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 8)
        );
        inputPanel.setBackground(BACKGROUND);

        JLabel targetLabel = new JLabel("Target:");
        targetLabel.setForeground(TEXT_COLOR);
        targetLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        targetField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        targetField.setBackground(PANEL_BACKGROUND);
        targetField.setForeground(TEXT_COLOR);
        targetField.setCaretColor(TEXT_COLOR);

        JLabel sizeLabel = new JLabel("Array size:");
        sizeLabel.setForeground(TEXT_COLOR);
        sizeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        sizeSelector.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        inputPanel.add(targetLabel);
        inputPanel.add(targetField);
        inputPanel.add(sizeLabel);
        inputPanel.add(sizeSelector);
        inputPanel.add(generateButton);
        inputPanel.add(searchButton);

        JSlider delaySlider = new JSlider(0, 1000, 200);
        delaySlider.setMajorTickSpacing(100);
        delaySlider.setMinorTickSpacing(50);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setBackground(BACKGROUND);
        delaySlider.setForeground(TEXT_COLOR);

        JPanel delayPanel = new JPanel(new BorderLayout(0, 5));
        delayPanel.setBackground(BACKGROUND);
        delayPanel.setBorder(new EmptyBorder(5, 10, 10, 10));

        JLabel delayLabel = new JLabel("Animation delay: 200 ms");
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
            animator.stop();
            int size = (int) sizeSelector.getSelectedItem();
            searchPanel.generateArray(size);
            statsPanel.resetStats();
        });

        searchButton.addActionListener(event -> startSearch());

        bottomPanel.add(inputPanel);
        bottomPanel.add(delayPanel);
        bottomPanel.add(statsPanel);

        return bottomPanel;
    }

    private void startSearch() {
        int target;

        try {
            target = Integer.parseInt(targetField.getText().trim());

            if (target < 1 || target > 100) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter an integer between 1 and 100.",
                    "Invalid target",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        animator.stop();

        int[] array = searchPanel.getArray();

        SearchAlgorithm algorithm;

        String selected = (String) algorithmSelector.getSelectedItem();

        if ("Binary Search".equals(selected)) {
            Arrays.sort(array);
            searchPanel.setArray(array);
            algorithm = new BinarySearch();
        } else {
            algorithm = new LinearSearch();
        }

        RecordingSearchObserver observer = new RecordingSearchObserver();

        long startTime = System.nanoTime();

        int result = algorithm.search(array, target, observer);

        long endTime = System.nanoTime();

        List<SearchEvent> events = observer.getEvents();

        long comparisons = events.stream()
                .filter(event -> event.type() == SearchEvent.Type.CHECK)
                .count();

        double elapsedTime = (endTime - startTime) / 1_000_000.0;

        statsPanel.updateStats((int) comparisons, elapsedTime, result);

        generateButton.setEnabled(false);
        animator.start(events);
    }

    private void styleButton(JButton button, Color color) {
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

