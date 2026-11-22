package algovisualizer.graph;

public record GraphEvent(
        Type type,
        String vertex
){
    public enum Type{
        VISIT,
        FINISH
    }
}
