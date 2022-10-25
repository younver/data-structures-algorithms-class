package structures.linkedlist;

import structures.node.DNode;

public class DoublyLinkedList<T extends Comparable<T>> {
    DNode<T> head;

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
        if (isEmpty()){
            head = new DNode<T>(val);
            return;
        }

        DNode<T> iterator = head;
        while (iterator.next != null){
            iterator = iterator.next;
        }
    }
}
