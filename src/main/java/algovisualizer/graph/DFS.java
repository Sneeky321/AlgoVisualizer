package algovisualizer.graph;

import algovisualizer.datastructures.MyStack;

import java.util.*;

public class DFS {
    public List<String> traverse(Graph graph, String start){
        List<String> result = new ArrayList<>();

        if(!graph.containsVertex(start)){
            return result;
        }

        MyStack<String> stack = new MyStack<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);

        while(!stack.isEmpty()){
            String current = stack.pop();

            if(!visited.contains(current)){
                visited.add(current);
                result.add(current);

                for(String neighbour : graph.getNeighbours(current)){
                    if(!visited.contains(neighbour)){
                        stack.push(neighbour);
                    }
                }
            }
        }

        return result;
    }
}
