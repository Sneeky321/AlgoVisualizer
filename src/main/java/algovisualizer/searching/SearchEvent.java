package algovisualizer.searching;

public record SearchEvent(
        Type type,
        int index
){
    public enum Type{
        CHECK,
        FOUND,
        FINISH
    }
}
