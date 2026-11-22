package algovisualizer.graph;

public interface GraphObserver {
    void onVisit(String vertex);
    void onFinish();
}
