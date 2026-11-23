package algovisualizer.ui.searching;

import algovisualizer.searching.SearchEvent;

import javax.swing.*;
import java.util.List;

public class SearchAnimator {
    private final SearchPanel searchPanel;
    private final Timer timer;
    private Runnable onAnimationFinished;

    private List<SearchEvent> events;
    private int currentIndex = 0;

    public SearchAnimator(SearchPanel searchPanel){
        this.searchPanel = searchPanel;

        timer = new Timer(500, e -> playNextEvent());
    }

    public void start(List<SearchEvent> events){
        stop();

        this.events = events;
        currentIndex = 0;

        searchPanel.clearHighlight();
        timer.start();
    }

    private void playNextEvent(){
        if(events == null || currentIndex >= events.size()){
            stop();
            return;
        }

        SearchEvent event = events.get(currentIndex);

        if(event.type() == SearchEvent.Type.CHECK ||
                event.type() == SearchEvent.Type.FOUND){

            searchPanel.setHighlightedOperation(
                    event.index(),
                    event.type()
            );
        }

        currentIndex++;

        if(currentIndex >= events.size()){
            timer.stop();

            if(onAnimationFinished != null){
                onAnimationFinished.run();
            }
        }
    }

    public void stop(){
        timer.stop();
    }

    public void setDelay(int delay){
        timer.setDelay(delay);
    }

    public void setOnAnimationFinished(Runnable callback){
        this.onAnimationFinished = callback;
    }
}
