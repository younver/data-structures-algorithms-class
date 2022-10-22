package exercises;

import structures.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        list.addEnd(4);
        list.addEnd(2);
        list.addEnd(8);

        list.display();
    }
}
