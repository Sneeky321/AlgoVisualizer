package algovisualizer.ui.graph;

import algovisualizer.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphView extends JPanel {
    private final Runnable onBack;

    public GraphView(Runnable onBack){
        this.onBack = onBack;

        setLayout(new BorderLayout());
        setBackground(new Color(30, 40, 50));

        Graph graph = new Graph();

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("A", "D");
        graph.addEdge("A", "C");
        graph.addEdge("B", "E");
        graph.addEdge("E", "F");

        GraphPanel graphPanel = new GraphPanel(graph);
        GraphControlPanel controlPanel = new GraphControlPanel(onBack);

        add(controlPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
    }
}
