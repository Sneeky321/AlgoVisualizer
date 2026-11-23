package algovisualizer.graph;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("A", "D");

        BFS bfs = new BFS();
        DFS dfs = new DFS();

        RecordingGraphObserver observer = new RecordingGraphObserver();

        System.out.println(bfs.traverse(graph, "A", observer));
        System.out.println(dfs.traverse(graph, "A"));

        for(GraphEvent event : observer.getEvents()){
            System.out.println(event);
        }
    }
}
