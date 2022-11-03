package structures.linkedlist;

import structures.node.DNode;

public class DoublyLinkedList<T extends Comparable> {
    protected DNode<T> head;

    public boolean isEmpty(){
        return head == null;
    }

    public void addToFront(T val){
        if (isEmpty()){
            head = new DNode<T>(val);
            return;
        }

        DNode<T> temp = new DNode<T>(val);
        temp.next = head;
        head.prev = temp;
        head = temp;
    }

    public void addToEnd(T val){
        DNode<T> newNode = new DNode<T>(val);

        if (isEmpty()){
            head = newNode;
            return;
        }

        DNode<T> iterator = head;
        while (iterator.next != null){
            iterator = iterator.next;
        }

        iterator.next = newNode;
        newNode.prev = iterator;
    }

    public void delete(T val){
        if (isEmpty()){
            System.out.println("~~ dlinkedlist is empty");
            return;
        }
        
        DNode<T> iterator = head;
        while (iterator != null){

            if (iterator.value.compareTo(val) == 0){
                if (iterator.prev != null)
                    iterator.prev.next = iterator.next;
                if (iterator.next != null)
                    iterator.next.prev = iterator.prev;
                break;
            }

            iterator = iterator.next;
        }
    }

    protected DNode<T> findNode(T val){
        DNode<T> iterator = head;

        while(iterator != null){
            if (iterator.value.compareTo(val) == 0){
                return iterator;
            }

            iterator = iterator.next;
        }

        return null;
    }

    protected int count(DNode<T> iterator, int count){
        if (iterator == null)
            return count;
        return count(iterator.next, ++count);
    }
    public int count(){
        return count(head, 0);
    }

    protected void swap(DNode<T> node1, DNode<T> node2){
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }
    
    protected DNode<T> minNode(DNode<T> startNode){
        DNode<T> minNode = startNode;
        DNode<T> iterator = startNode;

        while (iterator != null){
            if (iterator.value.compareTo(minNode.value) == -1){
                minNode = iterator;
            }

            iterator = iterator.next;
        }

        return minNode;
    }

    protected void sort(DNode<T> iterator){
        if (iterator == null) return;
        DNode<T> minNode = minNode(iterator);
        swap(iterator, minNode);
        sort(iterator.next);
    }
    public void sort(){
        sort(head);
    }

    public void display(){
        DNode<T> iterator = head;
        while (iterator != null){
            iterator.display();

            iterator = iterator.next;
        }
    }
}
