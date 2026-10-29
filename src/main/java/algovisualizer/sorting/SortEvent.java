package algovisualizer.sorting;

public record SortEvent(
        Type type,
        int firstIndex,
        int secondIndex,
        int value
) {
    public enum Type{
        COMPARE,
        SWAP,
        WRITE
    }
}
