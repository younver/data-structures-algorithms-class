package src;
public class TestStack {
    
    public static void main(String[] args) {
        StackOperations stackOps = new StackOperations();

        // first question region
        Stack stack = new Stack(8);

        stack.push(8);
        stack.push(2);
        stack.push(7);
        stack.push(2);
        stack.push(5);
        stack.push(2);

        stack.display();
        System.out.println("~~ initial stack\n");

        stackOps.popMin(stack);

        stack.display();
        System.out.println("~~ min popped stack\n");

        
        // second question region
        Stack stack2 = new Stack(8);

        stack2.push(9);
        stack2.push(3);
        stack2.push(11);
        stack2.push(23);
        stack2.push(97);
        stack2.push(23);
        stack2.push(42);
        stack2.push(7);

        stack2.display();
        System.out.println("~~ initial stack2\n");

        Stack sortedStack = stackOps.sortStacks(stack, stack2);

        sortedStack.display();
        System.out.println("~~ sorted stacks\n");


        // quality region
        stack.display();
        System.out.println("~~ last stack\n");

        stack2.display();
        System.out.println("~~ last stack2\n");
    }
}
