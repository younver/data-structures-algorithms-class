package structures.graph;

public class Edge {
    String vertexId;
    int weight;
    Edge nextEdge;

    public Edge(String vertexId, int weight){
        this.vertexId=vertexId;
        this.weight=weight;
    }
}
