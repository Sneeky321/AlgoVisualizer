package algovisualizer.sorting;

import java.util.ArrayList;
import java.util.List;

public class RecordingSortObserver implements SortObserver{
    private final List<SortEvent> events = new ArrayList<>();

    @Override
    public void onCompare(int firstIndex, int secondIndex){
        events.add(new SortEvent(
                SortEvent.Type.COMPARE,
                firstIndex,
                secondIndex,
                0
        ));
    }

    @Override
    public void onSwap(int firstIndex, int secondIndex){
        events.add(new SortEvent(
                SortEvent.Type.SWAP,
                firstIndex,
                secondIndex,
                0
        ));
    }

    @Override
    public void onWrite(int index, int value){
        events.add(new SortEvent(
                SortEvent.Type.WRITE,
                index,
                -1,
                value
        ));
    }

    public List<SortEvent> getEvents(){
        return List.copyOf(events);
    }
}
