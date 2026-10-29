package algovisualizer.ui;

import algovisualizer.sorting.BubbleSort;
import algovisualizer.sorting.RecordingSortObserver;
import algovisualizer.sorting.SortEvent;

import javax.swing.Timer;
import java.util.List;

public class SortAnimator {
    private final SortingPanel panel;
    private final Timer timer;

    private List<SortEvent> events;
    private int currentEventIndex;

    public SortAnimator(SortingPanel panel){
        this.panel = panel;

        timer = new Timer(50, event -> playNextEvent());
    }

    public void start(){
        if(timer.isRunning()){
            return;
        }

        int[] originalArray = panel.getArray().clone();

        RecordingSortObserver observer = new RecordingSortObserver();
        BubbleSort algorithm = new BubbleSort();

        algorithm.sort(originalArray.clone(), observer);

        events = observer.getEvents();
        currentEventIndex = 0;

        panel.setArray(originalArray.clone());

        if(events.isEmpty()){
            return;
        }

        timer.start();
    }

    private void playNextEvent(){
        if(currentEventIndex >= events.size()){
            stop();
            return;
        }

        SortEvent currentEvent = events.get(currentEventIndex);

        switch(currentEvent.type()){
            case COMPARE -> {
                panel.setHighlitedIndices(
                        currentEvent.firstIndex(),
                        currentEvent.secondIndex()
                );
            }

            case SWAP -> {
                int[] array = panel.getArray();

                int temp = array[currentEvent.firstIndex()];
                array[currentEvent.firstIndex()] =
                        array[currentEvent.secondIndex()];
                array[currentEvent.secondIndex()] = temp;


                panel.setHighlitedIndices(
                        currentEvent.firstIndex(),
                        currentEvent.secondIndex()
                );

            }

            case WRITE -> {
                panel.getArray()[currentEvent.firstIndex()] =
                        currentEvent.value();

                panel.setHighlitedIndices(
                        currentEvent.firstIndex(),
                        -1
                );
            }
        }

        panel.repaint();
        currentEventIndex++;

        if(currentEventIndex >= events.size()){
            timer.stop();
            panel.setHighlitedIndices(-1, -1);
        }
    }

    public void stop(){
        timer.stop();
        panel.setHighlitedIndices(-1, -1);
    }

    public boolean isRunning(){
        return timer.isRunning();
    }
}
