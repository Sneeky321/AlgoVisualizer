package algovisualizer.searching;

import java.util.ArrayList;
import java.util.List;

public class RecordingSearchObserver implements SearchObserver{
    private final List<SearchEvent> events = new ArrayList<>();

    @Override
    public void onCheck(int index) {
        events.add(new SearchEvent(SearchEvent.Type.CHECK, index));
    }

    @Override
    public void onFound(int index) {
        events.add(new SearchEvent(SearchEvent.Type.FOUND, index));
    }

    @Override
    public void onFinish(){
        events.add(new SearchEvent(SearchEvent.Type.FINISH, -1));
    }

    public List<SearchEvent> getEvents(){
        return List.copyOf(events);
    }
}
