package algovisualizer.ui.graph;

import algovisualizer.graph.BFS;
import algovisualizer.graph.DFS;
import algovisualizer.graph.Graph;
import algovisualizer.graph.GraphEvent;
import algovisualizer.graph.RecordingGraphObserver;
import algovisualizer.graph.GraphGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphView extends JPanel {
    private Graph graph;
    private final GraphGenerator generator = new GraphGenerator();
    private final GraphPanel graphPanel;
    private final GraphControlPanel controlPanel;
    private final GraphAnimator animator;

    public GraphView(Runnable onBack){
        setLayout(new BorderLayout());
        setBackground(new Color(30, 40, 50));

        graph = generator.generate(6);

        graphPanel = new GraphPanel(graph);
        controlPanel = new GraphControlPanel(onBack);
        animator = new GraphAnimator(graphPanel);

        controlPanel.setOnStart(this::startAlgorithm);
        controlPanel.setOnStop(animator::stop);
        controlPanel.setOnGenerate(this::generateGraph);

        add(controlPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
    }

    private void generateGraph(){
        animator.reset();

        graph = generator.generate(controlPanel.getVertexCount());

        graphPanel.setGraph(graph);

        controlPanel.updateStartVertices(graph.getVertices());
    }

    private void startAlgorithm(){
        if(animator.hasRemainingEvents()){
            animator.resume(controlPanel.getDelay());
            return;
        }

        RecordingGraphObserver observer = new RecordingGraphObserver();

        String algorithm = controlPanel.getSelectedAlgorithm();
        String startVertex = controlPanel.getSelectedStartVertex();

        if("BFS".equals(algorithm)){
            BFS bfs = new BFS();
            bfs.traverse(graph, startVertex, observer);
        } else if("DFS".equals(algorithm)){
            DFS dfs = new DFS();
            dfs.traverse(graph, startVertex, observer);
        }

        List<GraphEvent> events = observer.getEvents();

        animator.start(events, controlPanel.getDelay());
    }
}
