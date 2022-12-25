package structures.graph;

public class Graph<T extends Comparable> {
    private Vertex<T> head;

    public Vertex<T> findVertex(String vertexId) {
        
        Vertex<T> iterator = head;

        while (iterator != null) {
            if (iterator.id.compareTo(vertexId) == 0) {
                return iterator;
            }

            iterator = iterator.nextVertex;
        }

        return null;
    }

    public int inDegree(String vertexId){

        if (findVertex(vertexId) == null) {
            System.out.println("~~ no such vertex exists");
            return -1;
        }

        Vertex<T> vertexIterator = head;
        int inDegree = 0;

        while (vertexIterator != null) {

            Edge edgeIterator = vertexIterator.EdgeLink;

            edgeLoop : while (edgeIterator != null) {
                if (edgeIterator.vertexId.compareTo(vertexId) == 0){
                    ++inDegree;
                    break edgeLoop;
                }

                edgeIterator = edgeIterator.nextEdge;
            }

            vertexIterator = vertexIterator.nextVertex;
        }

        return inDegree;
    }

    public int outDegree(String id) {
        
        Vertex<T> vertex = findVertex(id);
        Edge edgeIterator = vertex.EdgeLink;
        
        int outDegree = 0;
        while (edgeIterator != null) {
            ++outDegree;

            edgeIterator = edgeIterator.nextEdge;
        }
        
        return outDegree;
    }

    public void addVertex(String id) {
        
        if (findVertex(id) != null){
            System.out.println("~~ this vertex already exists");
            return;
        }

        if (head == null){
            head = new Vertex<T>(id);
            return;
        }

        Vertex<T> iterator = head;
        while (iterator.nextVertex != null)
            iterator = iterator.nextVertex;

        iterator.nextVertex = new Vertex<T>(id);
    }

    public void addEdge(String idStart, String idEnd, int weight){
        
        Vertex<T> startVertex = findVertex(idStart);
        Vertex<T> endVertex = findVertex(idEnd);
        
        if (startVertex == null) {
            System.out.println("~~ no such start vertex exists");
            return;
        }
        
        if (endVertex == null){
            System.out.println("~~ no such end vertex exists");
            return;
        }

        Edge iterator = startVertex.EdgeLink;
        if (iterator == null){
            startVertex.EdgeLink = new Edge(idEnd, weight);
            return;
        }

        while (iterator.nextEdge != null)
            iterator = iterator.nextEdge;
        iterator.nextEdge = new Edge(idEnd, weight);
    }

    public void findNeighbours(String vertexId){
        
        Vertex<T> vertex = findVertex(vertexId);

        if (vertex == null) {
            System.out.println("~~ no such vertex exists");
            return;
        }

        System.out.print("~~ neighbours of " + vertexId + ": ");
        
        Edge iterator = vertex.EdgeLink;

        while (iterator != null) {
            System.out.print("" + iterator.vertexId + ", ");
            iterator=iterator.nextEdge;
        }

        System.out.println();
    }

    public void display() {
        
        Vertex<T> vertexIterator = head;

        while (vertexIterator != null) {
            System.out.print("~~ vertex " + vertexIterator.id + ": ");

            Edge edgeIterator = vertexIterator.EdgeLink;
        
            while (edgeIterator != null) {
                System.out.print("" + edgeIterator.vertexId + ", ");
                edgeIterator = edgeIterator.nextEdge;
            }

            vertexIterator = vertexIterator.nextVertex;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        
        Graph<String> graph = new Graph<>();

        graph.addVertex("0");
        for (int i=1; i < 5; i++){
            graph.addVertex(""+i);
            graph.addEdge("0", ""+i, 1);
        }

        graph.findNeighbours("0");

        graph.display();

        System.out.println(graph.outDegree("0"));
        System.out.println(graph.inDegree("1"));
    }
}
