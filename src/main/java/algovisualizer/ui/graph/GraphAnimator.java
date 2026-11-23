package algovisualizer.ui.graph;

import algovisualizer.graph.GraphEvent;

import javax.swing.Timer;
import java.util.*;

public class GraphAnimator {
    private final GraphPanel graphPanel;

    private List<GraphEvent> events;
    private final Set<String> visitedVertices = new HashSet<>();

    private int currentIndex;
    private String currentVertex;

    private Timer timer;

    public GraphAnimator(GraphPanel graphPanel){
        this.graphPanel = graphPanel;
    }

    public void start(List<GraphEvent> events, int delay){
        stop();

        this.events = new ArrayList<>(events);
        currentIndex = 0;
        currentVertex = null;

        visitedVertices.clear();

        graphPanel.setVisitedVertices(visitedVertices);
        graphPanel.setCurrentVertex(null);

        resume(delay);
    }

    public void resume(int delay){
        if(events == null || currentIndex >= events.size() || timer != null){
            return;
        }

        timer = new Timer(delay, e -> playNextEvent());
        timer.start();
    }

    public boolean hasRemainingEvents(){
        return events != null && currentIndex < events.size();
    }

    private void playNextEvent() {
        if (currentIndex >= events.size()) {
            stop();
            return;
        }

        GraphEvent event = events.get(currentIndex);
        currentIndex++;

        if (event.type() == GraphEvent.Type.VISIT) {
            if (currentVertex != null) {
                visitedVertices.add(currentVertex);
            }

            currentVertex = event.vertex();

            graphPanel.setVisitedVertices(visitedVertices);
            graphPanel.setCurrentVertex(currentVertex);

        } else if (event.type() == GraphEvent.Type.FINISH) {
            if (currentVertex != null) {
                visitedVertices.add(currentVertex);
            }

            currentVertex = null;

            graphPanel.setVisitedVertices(visitedVertices);
            graphPanel.setCurrentVertex(null);

            stop();
        }
    }

    public void stop(){
        if(timer != null){
            timer.stop();
            timer = null;
        }
    }

    public void reset(){
        stop();

        events = null;
        currentIndex = 0;
        currentVertex = null;

        visitedVertices.clear();

        graphPanel.setVisitedVertices(visitedVertices);
        graphPanel.setCurrentVertex(null);
    }
}
