package algovisualizer.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private static final Color BACKGROUND = new Color(30, 40, 50);
    private static final Color PANEL_BACKGROUND = new Color(40, 50, 60);
    private static final Color TEXT_COLOR = new Color(225, 230, 240);

    public MainMenuPanel(Runnable onSortingSelected,
                         Runnable onGraphSelected,
                         Runnable onSearchSelected)
    {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel title = new JLabel("AlgoVisualizer", SwingConstants.CENTER);
        title.setForeground(TEXT_COLOR);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));

        JLabel subtitle = new JLabel(
                "Algorithm and Data Structure Visualizer",
                SwingConstants.CENTER
        );
        subtitle.setForeground(new Color(155, 170, 190));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 8));
        headerPanel.setBackground(BACKGROUND);
        headerPanel.add(title);
        headerPanel.add(subtitle);

        JPanel modulesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        modulesPanel.setBackground(BACKGROUND);
        modulesPanel.setBorder(new EmptyBorder(50, 30, 50, 30));

        JButton sortingButton = createModuleButton(
                "Sorting Algorithms",
                "Visualize sorting algorithms step by step"
        );

        JButton graphButton = createModuleButton(
                "Graph Algorithms",
                "Explore graph traversal algorithms"
        );

        JButton searchingButton = createModuleButton(
                "Search Algorithms",
                "Visualize search algorithms"
        );

        JButton aboutButton = createModuleButton(
                "About",
                "Information about the application"
        );

        sortingButton.addActionListener(e -> onSortingSelected.run());

        graphButton.addActionListener(e -> onGraphSelected.run());

        searchingButton.addActionListener(e -> onSearchSelected.run());

        aboutButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "AlgoVisualizer\nAlgorithm and Data Structure Visalizer",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                )
        );

        modulesPanel.add(sortingButton);
        modulesPanel.add(graphButton);
        modulesPanel.add(searchingButton);
        modulesPanel.add(aboutButton);

        add(headerPanel, BorderLayout.NORTH);
        add(modulesPanel, BorderLayout.SOUTH);
    }

    private JButton createModuleButton(String title, String description){
        JButton button = new JButton();

        button.setLayout(new BorderLayout(0, 10));
        button.setBackground(PANEL_BACKGROUND);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(20, 20, 20, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(TEXT_COLOR);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descriptionLabel.setForeground(new Color(155, 170, 190));

        button.add(titleLabel, BorderLayout.CENTER);
        button.add(descriptionLabel, BorderLayout.SOUTH);

        return button;
    }
}
