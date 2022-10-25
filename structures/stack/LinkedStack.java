package structures.stack;

import structures.node.Node;

public class LinkedStack<T extends Comparable<T>>{
    Node<T> top;

    public boolean isEmpty(){
        return top == null;
    }

    public int count(){
        int counter = 0;
        Node<T> iterator = top;
        while(iterator != null){
            counter++;
            iterator = iterator.next;
        }

        return counter;
    }

    public void push(T val){
        Node<T> temp = new Node<T>(val);

        // empty
        if (isEmpty()){
            top = temp;
            return;
        }

        // add to front
        temp.next = top;
        top = temp;
    }

    public T pop(){
        T popValue = null;

        // handle empty
        if (isEmpty()){
            return popValue;
        }

        popValue = top.value;
        top = top.next;

        return popValue;
    }

    public T peak(){
        // handle empty
        if (top == null){
            System.out.println("~~ empty stack.");
            return null;
        }
        
        T output = pop();
        push(output);
        return output;
    }

    public void delete(T val){
        if (isEmpty()){
            System.out.println("~~ empty stack.");
        }

        Node<T> iterator = top;
        while(iterator.next != null){
            if (iterator.next.value.compareTo(val) == 0){
                iterator.next = iterator.next.next;
                return;
            }

            iterator = iterator.next;
        }
    }
    
    public void display(){
        Node<T> iterator = top;
        while (iterator != null){
            System.out.println(iterator);
            iterator = iterator.next;
        }
        System.out.println("#~~~~~#");
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.display();

        stack.pop();
        stack.display();

        stack.pop();
        stack.pop();
        stack.display();

        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.display();

        System.out.println("~~ peak: " + stack.peak());
        stack.display();
    }


}
