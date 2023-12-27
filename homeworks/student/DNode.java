package homeworks.student;

public class DNode<T extends Comparable>{
    public T value;
    public DNode<T> prev, next;

    public DNode(T value){
        this.value = value;
        prev = null;
        next = null;
    }

    public void display(){
        System.out.println(value.toString());
    }
}
