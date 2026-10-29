package algovisualizer.sorting;

public interface SortObserver {
    void onCompare(int firstIndex, int secondIndex);

    void onSwap(int firstIndex, int secondIndex);

    void onWrite(int index, int value);
}
