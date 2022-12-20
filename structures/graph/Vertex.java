package structures.graph;

public class Vertex<T extends Comparable> {
    String id;
    T value;
    Vertex<T> nextVertex;
    Edge EdgeLink;
    
    public Vertex(String id){
        value=null;
        this.id=id;
    }
    public Vertex(String id, T val){
        this.value=val;
        this.id=id;
    }
    public String toString(){
        return id;
    }
}
