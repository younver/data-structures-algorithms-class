package structures.linkedlist;
import structures.Node;

public class CircularLinkedList<T extends Comparable<T>> {
    Node<T> head;

    

    public void addToFront(T val){
        
        // handle empty
        if (head == null){
            head = new Node<T>(val);
            head.next = head;
            return;
        }

        Node<T> iterator = head;
        while (iterator.next != head){
            iterator = iterator.next;
        }

        Node<T> newNode = new Node<T>(val);
        newNode.next = head;
        iterator.next = newNode;
        head = newNode;
    }

    public void addToEnd(T val){
        
        // handle empty
        if (head == null){
            head = new Node<T>(val);
            head.next = head;
            return;
        }

        Node<T> iterator = head;
        while (iterator.next != head){
            iterator = iterator.next;
        }

        Node<T> newNode = new Node<T>(val);
        newNode.next = head;
        iterator.next = newNode;
    }
}
