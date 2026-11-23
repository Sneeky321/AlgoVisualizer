package algovisualizer.ui.graph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GraphControlPanel extends JPanel {
    private static final Color BACKGROUND = new Color(30, 40, 50);
    private static final Color PANEL_BACKGROUND = new Color(40, 50, 60);
    private static final Color TEXT_COLOR = new Color(225, 230, 240);

    private final JComboBox<String> algorithmSelector;
    private final JComboBox<String> startVertexSelector;

    private final JButton startButton;
    private final JButton stopButton;

    private final JSlider delaySlider;
    private final JLabel delayLabel;

    public GraphControlPanel(Runnable onBack){
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(15, 20, 15, 20));

        algorithmSelector = new JComboBox<>(new String[]{
                "BFS", "DFS"
        });

        startVertexSelector = new JComboBox<>(new String[]{
                "A", "B", "C", "D", "E", "F"
        });

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        JButton backButton = new JButton("Back");

        styleComboBox(algorithmSelector);
        styleComboBox(startVertexSelector);

        styleButton(startButton, new Color(50, 140, 105));
        styleButton(stopButton, new Color(170, 75, 75));
        styleButton(backButton, new Color(65, 85, 115));

        backButton.addActionListener(e -> onBack.run());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.setBackground(BACKGROUND);

        topPanel.add(backButton);
        topPanel.add(new JLabel("Algorithm:"));
        topPanel.add(algorithmSelector);
        topPanel.add(new JLabel("Start vertex:"));
        topPanel.add(startVertexSelector);

        for(Component component : topPanel.getComponents()){
            if(component instanceof JLabel label){
                label.setForeground(TEXT_COLOR);
            }
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        bottomPanel.setBackground(BACKGROUND);

        bottomPanel.add(startButton);
        bottomPanel.add(stopButton);

        delaySlider = new JSlider(100, 1500, 700);
        delaySlider.setMajorTickSpacing(350);
        delaySlider.setMinorTickSpacing(50);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setBackground(BACKGROUND);
        delaySlider.setForeground(TEXT_COLOR);

        delayLabel = new JLabel("Animation delay: 700 ms");
        delayLabel.setForeground(TEXT_COLOR);

        JPanel delayPanel = new JPanel(new BorderLayout(0, 5));
        delayPanel.setBackground(BACKGROUND);

        delayPanel.add(delayLabel, BorderLayout.NORTH);
        delayPanel.add(delaySlider, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(BACKGROUND);

        controlPanel.add(bottomPanel, BorderLayout.NORTH);
        controlPanel.add(delayPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
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

    private void styleComboBox(JComboBox<String> comboBox){
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setForeground(TEXT_COLOR);
        comboBox.setBackground(PANEL_BACKGROUND);
        comboBox.setFocusable(false);
    }
}
