package algovisualizer.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AlgorithmInfoDialog extends JDialog {
    private static final Color BACKGROUND = new Color(30, 40, 50);
    private static final Color PANEL_BACKGROUND = new Color(40, 50, 60);
    private static final Color TEXT_COLOR = new Color(225,  230, 240);
    private static final Color SECONDARY_COLOR = new Color(155, 170, 190);

    public AlgorithmInfoDialog(Window owner){
        super(owner, "About AlgoVisualizer", ModalityType.APPLICATION_MODAL);

        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND);

        JLabel title = new JLabel("AlgoVisualizer", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(TEXT_COLOR);

        JLabel subtitle = new JLabel(
                "Algorithm and Data Structure Visualizer",
                SwingConstants.CENTER
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(SECONDARY_COLOR);

        JPanel header = new JPanel(new GridLayout(2, 1, 0, 5));
        header.setBackground(BACKGROUND);
        header.setBorder(new EmptyBorder(20, 10, 15, 10));
        header.add(title);
        header.add(subtitle);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Sorting", createScrollPane(createSortingPanel()));
        tabs.addTab("Graph", createScrollPane(createGraphPanel()));
        tabs.addTab("Search", createScrollPane(createSearchPanel()));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 15, 10));
        bottomPanel.add(closeButton);

        add(header, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(650, 550);
        setMinimumSize(new Dimension(550, 450));
        setLocationRelativeTo(owner);
    }

    private JPanel createSortingPanel() {
        JPanel panel = createBasePanel();

        addAlgorithmInfo(panel, "Bubble Sort", "O(n²)",
                "Compares adjacent elements and swaps them if they are in the wrong order. Repeats this process until the array is sorted.");

        addAlgorithmInfo(panel, "Insertion Sort", "O(n²)",
                "Builds a sorted part of the array by inserting each new element into its correct position.");

        addAlgorithmInfo(panel, "Selection Sort", "O(n²)",
                "Finds the smallest element in the unsorted part of the array and moves it to the beginning of that part.");

        addAlgorithmInfo(panel, "Merge Sort", "O(n log n)",
                "Divides the array into smaller parts, sorts them, and then merges them into one sorted array.");

        return panel;
    }

    private JPanel createGraphPanel() {
        JPanel panel = createBasePanel();

        addAlgorithmInfo(panel, "BFS - Breadth-First Search", "O(V + E)",
                "Explores the graph level by level. First visits the nearest neighbors, then moves on to the next level. Uses a Queue.");

        addAlgorithmInfo(panel, "DFS - Depth-First Search", "O(V + E)",
                "Explores one path as deeply as possible. When it cannot continue, it backtracks and explores other paths. Uses a Stack.");

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = createBasePanel();

        addAlgorithmInfo(panel, "Linear Search", "O(n)",
                "Checks array elements one by one until the target is found or the end of the array is reached.");

        addAlgorithmInfo(panel, "Binary Search", "O(log n)",
                "Searches for an element in a sorted array. At each step, it checks the middle element and eliminates half of the remaining search range. The array must be sorted.");

        return panel;
    }

    private JPanel createBasePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_BACKGROUND);
        panel.setBorder(new EmptyBorder(15,20,15,20));
        return panel;
    }

    private void addAlgorithmInfo(
            JPanel panel,
            String name,
            String complexity,
            String description
    ) {
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel complexityLabel = new JLabel("Time complexity: " + complexity);
        complexityLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        complexityLabel.setForeground(new Color(100, 190, 160));
        complexityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descriptionArea.setForeground(SECONDARY_COLOR);
        descriptionArea.setBackground(PANEL_BACKGROUND);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(complexityLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(descriptionArea);
        panel.add(Box.createVerticalStrut(20));
    }

    private JScrollPane createScrollPane(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.getViewport().setBackground(PANEL_BACKGROUND);

        return scrollPane;
    }
}
