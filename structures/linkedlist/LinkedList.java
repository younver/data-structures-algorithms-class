package structures.linkedlist;
import structures.Node;

public class LinkedList<T extends Comparable<T>> {
    public Node<T> head;

    public Node<T> createNode(T val){
        return new Node<T>(val);
    }

    public void addFront(T val){
        Node<T> newNode = createNode(val);
        newNode.next = head;
        head = newNode;
    }

    public void addRightAfterHead(T val){
        // if list is empty
        if (head == null){
            addFront(val);
            return;
        }

        Node<T> newNode = createNode(val);
        newNode.next = head.next;
        head.next = newNode;
    }

    public void display(){
        Node<T> iterator = head;
        
        while(iterator!=null){
            System.out.println(iterator);
            iterator = iterator.next;
        }
    }

    /* enhanced methods */
    public void add(T val, Node<T> tempNode){    
        
        // base case
        if (tempNode.next == null){
            tempNode.next = createNode(val);
            return;
        }

        add(val, tempNode.next);
    }
    public void add(T val){        
        
        // empty list
        if (head == null){
            head = createNode(val);
            return;
        }

        add(val, head);
    }

    public T min(){
        // handle empty
        if (head == null){
            return null;
        }

        T min = head.value;
        Node<T> iterator = head.next;

        while (iterator != null){
            if (iterator.value.compareTo(min) == -1){
                min = iterator.value;
            }

            iterator = iterator.next;
        }

        return min;
    }

    public LinkedList<T> selectionSort(LinkedList<T> list, LinkedList<T> sortedList){
        // base case
        if(list.head == null){
            return sortedList;
        }
        
        // selection sort
        T min = list.min();
        list.delete(min);
        sortedList.add(min);
        
        // recursive
        return selectionSort(list, sortedList);
    }
    public LinkedList<T> selectionSort(){
        return selectionSort(copy(), new LinkedList<>());
    }

    // requirement: sorted list (ascending)
    public void sortedAdd(T val, Node<T> tempNode){
        
        // if element is smaller than val, addFront
        if (tempNode.value.compareTo(val) == 1){
            
            Node<T> newNode = createNode(val);
            newNode.next = tempNode;
            tempNode = newNode;
            
            return;
        }

        // if list finished
        if (tempNode.next == null){
            tempNode.next = createNode(val);
            return;
        }
    
        sortedAdd(val, tempNode.next);
    }
    public void sortedAdd(T val){

        // empty list
        if (head == null){
            head = createNode(val);
            return;
        }

        sortedAdd(val, head);
    }

    // count
    public int count(T val){
        int counter = 0;

        // handle empty
        if (head == null){
            return counter;
        }

        // iterate
        Node<T> iterator = head;
        while (iterator != null){
            if (iterator.value.compareTo(val) == 0){
                counter++;
                iterator = iterator.next;
            }
        }

        return counter;
    }
    public int count(){
        int counter = 0;

        // handle empty
        if (head == null){
            return counter;
        }

        // iterate
        Node<T> iterator = head;
        while (iterator != null){
            counter++;
            iterator = iterator.next;
        }

        return counter;
    }

    // search
    public boolean search(T val){
        // handle empty
        if (head == null){
            return false;
        }

        Node<T> iterator = head;

        while (iterator != null){
            if (val.compareTo(iterator.value) == 0){
                return true;
            }

            iterator = iterator.next;
        }

        // not found
        return false;
    }

    // search recursive
    public boolean searchRecursively(T val, Node<T> iterator){
        // end case
        if (iterator == null){
            return false;
        }

        // base case
        if (iterator.value.compareTo(val) == 0){
            return true;
        }

        return searchRecursively(val, iterator.next);
    }
    public boolean searchRecursively(T val){
        return searchRecursively(val, head);
    }

    // delete ~~ idea of predecessor-successor
    public void delete(T val){
        // case not exist & empty
        if (search(val) == false){
            return;
        }

        // case head
        if (head.value.compareTo(val) == 0){
            head = head.next;
            return;
        }

        // init
        Node<T> iterator = head;
        Node<T> prev = head;

        // iterate 'til val found
        while(iterator.value.compareTo(val) != 0 ){
            prev = iterator;
            iterator = iterator.next;
        }

        // delete ~~ break connections of node to be deleted
        prev.next = iterator.next;
    }

    // TODO: multi-delete
    public void deleteMultiple(T val, int count){
        
    }

    // homework-2 methods
    public int searchAnalyze(T val, Node<T> iterator, int count){
        count++;

        if (iterator.value.compareTo(val) == 0 | iterator == null){
            return count;
        }

        return searchAnalyze(val, iterator.next, count);
    }
    public int searchAnalyze(T val){
        return searchAnalyze(val, head, 0);
    }

    public int searchPushAnalyze(T val, Node<T> iterator, int count){
        count++;

        if (iterator == null){
            return count;
        }

        if (iterator.value.compareTo(val) == 0){
            addFront(val);
            return count;
        }

        return searchPushAnalyze(val, iterator.next, count);
    }
    public int searchPushAnalyze(T val){
        return searchPushAnalyze(val, head, 0);
    }

    public LinkedList<T> copy(){
        LinkedList<T> newList = new LinkedList<>();
        Node<T> iterator = head;

        while(iterator != null){
            newList.add(iterator.value);
            iterator = iterator.next;
        }

        return newList;
    }
}
