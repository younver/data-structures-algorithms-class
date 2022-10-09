package week3.queue;

import java.util.ArrayList;

public class Queue<T> {
    private int head = -1;
    private int tail = -1;
    private ArrayList<T> arr = new ArrayList<>();

    public void dequeue(){
        // if queue is empty
        if (this.isEmpty())
            System.out.println("~~ empty queue.");
        
        // if queue has only one element
        else if (head == tail)
            head = tail = -1;

        // if queue has more than one element
        else
            tail++;
    }

    public void enqueue(T val){
        // if queue is empty
        if (this.isEmpty()){
            head = 0;
            tail = 0;
            arr.add(val);
        }
        // if queue is not empty
        else {
            head++;

            if (arr.size() > this.head)
                // move head pointer to next
                arr.set(head, val);

            else
                // add element to the queue
                arr.add(val);
        }

    }

    public T head(){
        if (this.head == -1){
            return null;
        }

        return arr.get(head);
    }

    public T tail(){
        if (this.tail == -1){
            return null;
        }

        return arr.get(tail);
    }

    public boolean isEmpty(){
        if (this.head == -1 && this.tail == -1)
            return true;
        return false;
    }

    public void clear(){

    }
}
