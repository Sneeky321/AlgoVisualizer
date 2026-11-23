package algovisualizer.graph;

import algovisualizer.datastructures.MyStack;

import java.util.*;

public class DFS {
    public List<String> traverse(Graph graph, String start){
        return traverse(graph, start, null);
    }

    public List<String> traverse(Graph graph, String start, GraphObserver observer){
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

                if(observer != null){
                    observer.onVisit(current);
                }

                for(String neighbour : graph.getNeighbours(current)){
                    if(!visited.contains(neighbour)){
                        stack.push(neighbour);
                    }
                }
            }
        }

        if(observer != null){
            observer.onFinish();
        }

        return result;
    }
}
