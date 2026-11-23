package algovisualizer.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
    private final Random random = new Random();

    public Graph generate(int vertexCount){
        if(vertexCount < 4 || vertexCount > 12){
            throw new IllegalArgumentException("Vertex count must be between 4 and 12");
        }

        Graph graph = new Graph();
        List<String> vertices = new ArrayList<>();

        for(int i = 0; i < vertexCount; i++){
            String vertex = String.valueOf((char) ('A' + i));

            vertices.add(vertex);
            graph.addVertex(vertex);
        }

        for(int i = 1; i < vertexCount; i++){
            int parentIndex = random.nextInt(i);

            graph.addEdge(
                    vertices.get(i),
                    vertices.get(parentIndex)
            );
        }

        int extraEdges = vertexCount / 2;

        for(int i = 0; i < extraEdges; i++){
            int firstIndex = random.nextInt(vertexCount);
            int secondIndex = random.nextInt(vertexCount);

            if(firstIndex != secondIndex){
                String first = vertices.get(firstIndex);
                String second = vertices.get(secondIndex);

                if(!graph.getNeighbours(first).contains(second)){
                    graph.addEdge(first, second);
                }
            }
        }

        return graph;
    }
}
