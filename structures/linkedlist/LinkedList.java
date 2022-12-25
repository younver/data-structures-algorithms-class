package structures.linkedlist;
import structures.node.Node;

public class LinkedList<T extends Comparable> {
    public Node<T> head;

    public Node<T> createNode(T val){
        return new Node<T>(val);
    }

    public boolean isEmpty(){
        return head == null;
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

        if (head == null){
            System.out.println("~~ linkedlist is empty");
        }
        
        while(iterator!=null){
            System.out.print(iterator.toString() + ", ");
            iterator = iterator.next;
        }
        System.out.println();
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

    public boolean isSorted(Node<T> iterator){
        // base case
        if (iterator.next == null){
            return true;
        }

        // end case
        if (iterator.value.compareTo(iterator.next.value) != -1){
            return false;
        }

        // recurse
        return isSorted(iterator.next);
    }
    public boolean isSorted(){
        // empty & head case
        if (head == null || head.next == null){
            return true;
        }

        return isSorted(head);
    }

    // requirement: sorted list (ascending)
    public void sortedAdd(T val, Node<T> iterator, Node<T> prev){

        // if list finished
        if (iterator == null){
            prev.next = createNode(val);
            return;
        }
        
        // if element is smaller than val, addFront
        if (val.compareTo(iterator.value) == -1){
            
            Node<T> newNode = createNode(val);
            newNode.next = iterator;
            prev.next = newNode;
            
            return;
        }
        
        sortedAdd(val, iterator.next, prev.next);
    }
    public void sortedAdd(T val){

        // empty list
        if (head == null){
            head = createNode(val);
            return;
        }

        // head case
        if (head.value.compareTo(val) == 1){
            Node<T> temp = new Node<T>(val);
            temp.next = head;
            head = temp;
            return;
        }

        sortedAdd(val, head.next, head);
    }

    // count
    public int count(T val){
        int count = 0;

        // handle empty
        if (head == null){
            return count;
        }

        // iterate 
        Node<T> iterator = head;
        while (iterator != null){
            if (iterator.value.compareTo(val) == 0){
                count++;
            }
            iterator = iterator.next;
        }

        return count;
    }
    public int count(){
        int count = 0;

        // handle empty
        if (head == null){
            return count;
        }

        // iterate
        Node<T> iterator = head;
        while (iterator != null){
            count++;
            iterator = iterator.next;
        }

        return count;
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

    public T popFront(){
        if (isEmpty()){
            return null;
        }

        T value = head.value;
        head = this.head.next;
        return value;
    }

    // max
    public T max(){
        
        // empty list
        if (head == null){
            System.out.println("~~ linkedlist is empty");
            return null;
        }
        
        Node<T> iterator = head;
        T max = head.value;
        while (iterator != null){

            if (iterator.value.compareTo(max) == 1)
                max = iterator.value;

            iterator = iterator.next;
        }

        return max;
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

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.sortedAdd(3);
        list.sortedAdd(0);
        list.sortedAdd(8);
        list.sortedAdd(5);
        list.sortedAdd(-87);
        list.sortedAdd(99);
        list.sortedAdd(-1);
        list.display();

        System.out.println("~~ is sorted: " + list.isSorted());
    
        list.add(5);
        list.display();
        System.out.println("~~ is sorted: " + list.isSorted());

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(3);
        list1.display();
        System.out.println("~~ is sorted: " + list1.isSorted());
    }
}
