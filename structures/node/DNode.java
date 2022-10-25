package structures.node;

public class DNode<T extends Comparable<T>>{
    public T value;
    public DNode<T> prev, next;

    public DNode(T value){
        this.value = value;
        prev = null;
        next = null;
    }
}
