package algorithms;

import structures.linkedlist.LinkedList;
import structures.node.Node;

public class Sort<T extends Comparable> {
    
    public LinkedList<T> selectionSort(LinkedList<T> referenceList) {
        // selection sort implementation for linked lists ~ O(n^2)

        if (referenceList.head == null) {
            System.out.println("~~ list is empty");
            return null;
        }

        LinkedList<T> list = referenceList.copy();

        Node<T> iterator = list.head;
        sortLoop : while (iterator != null) {

            // find min
            Node<T> minNode = iterator;

            Node<T> minIterator = iterator.next;
            minLoop : while (minIterator != null) {
                
                if (minIterator.value.compareTo(minNode.value) == -1) {
                    minNode = minIterator;
                }

                minIterator = minIterator.next;
            }

            // swap
            T min = minNode.value;
            minNode.value = iterator.value;
            iterator.value = min;

            iterator = iterator.next;
        }

        return list;
    }

    public LinkedList<T> insertionSort(LinkedList<T> referenceList) {
        // insertion sort implementation for linked lists
        // best O(n) ~ worst O(n^2)

        if (referenceList.head == null) {
            System.out.println("~~ list is empty");
            return null;
        }

        LinkedList<T> list = referenceList.copy();

        return list;
    }

    public static void main(String[] args) {
        
        Sort<Integer> sorter = new Sort<>();
        
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(6);
        list.add(2);
        list.display();
        
        LinkedList<Integer> sortedList;

        System.out.println("~~ selection sort");
        sortedList = sorter.selectionSort(list);
        sortedList.display();

        System.out.println("~~ insertion sort");
        sortedList = sorter.insertionSort(list);
        sortedList.display();
    }
}
