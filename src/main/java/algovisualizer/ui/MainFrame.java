package algovisualizer.ui;

import algovisualizer.ui.sorting.SortingView;
import algovisualizer.ui.graph.GraphView;
import algovisualizer.ui.searching.SearchView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private static final String MENU = "MENU";
    private static final String SORTING = "SORTING";
    private static final String GRAPH = "GRAPH";
    private static final String SEARCHING = "SEARCHING";

    public MainFrame(){
        super("AlgoVisualizer");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenuPanel menuPanel = new MainMenuPanel(
                () -> cardLayout.show(mainPanel, SORTING),
                () -> cardLayout.show(mainPanel, GRAPH),
                () -> cardLayout.show(mainPanel, SEARCHING)
        );

        SortingView sortingView = new SortingView(
                () -> cardLayout.show(mainPanel, MENU)
        );

        GraphView graphView = new GraphView(
                () -> cardLayout.show(mainPanel, MENU)
        );

        SearchView searchView = new SearchView(
                () -> cardLayout.show(mainPanel, MENU)
        );

        mainPanel.add(menuPanel, MENU);
        mainPanel.add(sortingView, SORTING);
        mainPanel.add(graphView, GRAPH);
        mainPanel.add(searchView, SEARCHING);

        add(mainPanel);

        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout.show(mainPanel, MENU);
    }
}
