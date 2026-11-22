package algovisualizer.graph;

import algovisualizer.datastructures.MyQueue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFS {

    public List<String> traverse(Graph graph, String start){
        List<String> result = new ArrayList<>();

        if(!graph.containsVertex(start)){
            return result;
        }

        MyQueue<String> queue = new MyQueue<>();
        Set<String> visited = new HashSet<>();

        queue.enqueue(start);
        visited.add(start);

        while(!queue.isEmpty()){
            String current = queue.dequeue();
            result.add(current);

            for(String neighbour : graph.getNeighbours(current)){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.enqueue(neighbour);
                }
            }
        }

        return result;
    }
}
