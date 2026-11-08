package algovisualizer.ui;

import algovisualizer.sorting.BubbleSort;
import algovisualizer.sorting.RecordingSortObserver;
import algovisualizer.sorting.SortEvent;
import algovisualizer.sorting.SortingAlgorithm;

import javax.swing.Timer;
import java.util.List;

public class SortAnimator {
    private SortingAlgorithm algorithm;
    private final SortingPanel panel;
    private final SortingStatsPanel statsPanel;
    private long sortTimeNs;
    private final Timer timer;
    private Runnable onAnimationFinished;

    private List<SortEvent> events;
    private int currentEventIndex;

    public SortAnimator(SortingPanel panel, SortingStatsPanel statsPanel,
                        SortingAlgorithm algorithm){
        this.panel = panel;
        this.statsPanel = statsPanel;
        this.algorithm = algorithm;

        timer = new Timer(50, event -> playNextEvent());
    }

    public void start(){
        if(timer.isRunning()){
            return;
        }

        int[] originalArray = panel.getArray().clone();

        RecordingSortObserver observer = new RecordingSortObserver();

        long startTime = System.nanoTime();
        algorithm.sort(originalArray.clone(), observer);
        sortTimeNs = System.nanoTime() - startTime;

        events = observer.getEvents();
        currentEventIndex = 0;

        panel.setArray(originalArray.clone());
        countEvents();

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
                panel.setHighlightedOperation(
                        currentEvent.firstIndex(),
                        currentEvent.secondIndex(),
                        SortEvent.Type.COMPARE
                );
            }

            case SWAP -> {
                int[] array = panel.getArray();

                int temp = array[currentEvent.firstIndex()];
                array[currentEvent.firstIndex()] =
                        array[currentEvent.secondIndex()];
                array[currentEvent.secondIndex()] = temp;


                panel.setHighlightedOperation(
                        currentEvent.firstIndex(),
                        currentEvent.secondIndex(),
                        SortEvent.Type.SWAP
                );

            }

            case WRITE -> {
                panel.getArray()[currentEvent.firstIndex()] =
                        currentEvent.value();

                panel.setHighlightedOperation(
                        currentEvent.firstIndex(),
                        -1,
                        SortEvent.Type.WRITE
                );
            }
        }

        panel.repaint();
        currentEventIndex++;

        if(currentEventIndex >= events.size()){
            if(onAnimationFinished != null){
                onAnimationFinished.run();
            }
            timer.stop();
            panel.clearHighlight();
        }
    }

    public void stop(){
        timer.stop();
        panel.clearHighlight();
    }

    public boolean isRunning(){
        return timer.isRunning();
    }

    public void setOnAnimationFinished(Runnable onAnimationFinished){
        this.onAnimationFinished = onAnimationFinished;
    }

    public void countEvents(){
        int compareCount = 0;
        int swapCount = 0;
        int writeCount = 0;

        for(SortEvent event : events){
            switch(event.type()){
                case COMPARE -> compareCount++;
                case SWAP -> swapCount++;
                case WRITE -> writeCount++;
            }
        }

        statsPanel.updateStats(
                sortTimeNs,
                compareCount,
                swapCount,
                writeCount
        );
    }

    public void setDelay(int delay){
        timer.setDelay(delay);
    }

    public void setAlgorithm(SortingAlgorithm algorithm){
        if(!timer.isRunning()){
            this.algorithm = algorithm;
        }
    }
}
