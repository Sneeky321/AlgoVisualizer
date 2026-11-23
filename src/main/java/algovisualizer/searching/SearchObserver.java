package algovisualizer.searching;

public interface SearchObserver {
    void onCheck(int index);
    void onFound(int index);
    void onFinish();
}
