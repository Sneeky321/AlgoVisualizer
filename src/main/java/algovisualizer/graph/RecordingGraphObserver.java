package algovisualizer.graph;

import java.util.*;

public class RecordingGraphObserver implements GraphObserver{
    private final List<GraphEvent> events = new ArrayList<>();

    @Override
    public void onVisit(String vertex){
        events.add(new GraphEvent(GraphEvent.Type.VISIT, vertex));
    }

    @Override
    public void onFinish(){
        events.add(new GraphEvent(GraphEvent.Type.FINISH, null));
    }

    public List<GraphEvent> getEvents(){
        return events;
    }
}

