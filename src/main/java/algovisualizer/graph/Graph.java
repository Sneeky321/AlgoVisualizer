package algovisualizer.graph;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph(){
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex){
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String vertex1, String vertex2){
        addVertex(vertex1);
        addVertex(vertex2);

        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }

    public List<String> getNeighbours(String vertex){
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public boolean containsVertex(String vertex){
        return adjacencyList.containsKey(vertex);
    }

    public List<String> getVertices(){
        return new ArrayList<>(adjacencyList.keySet());
    }
}
