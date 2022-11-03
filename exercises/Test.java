package exercises;

import structures.linkedlist.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        list.add(4);
        list.add(2);
        list.add(8);

        list.display();
    }
}
