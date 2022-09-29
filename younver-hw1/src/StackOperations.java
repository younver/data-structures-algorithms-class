package src;


/* 
 * 1 - Write a method or methods to remove the smallest element from the stack. You should
 * preserve the stack values. For example if you remove minimum element from the following
 * stack, the resulting stack should be like the one on the right.
 * 
 * 2 - Write a method that takes two unsorted stacks as an argument, merges them into a sorted
 * stack and returns this new stack
 */

public class StackOperations {

    public static int findMin(Stack stack, boolean pop)
    {    
        Stack temporaryStack = new Stack(stack.size());
        int minElement = stack.pop();
        temporaryStack.push(minElement);

        while(!stack.isEmpty())
        {
            int currentElement = stack.pop();
            
            temporaryStack.push(currentElement);

            if(currentElement < minElement){
                minElement = currentElement;
            }
        }

        boolean isPopped = false;
        while(!temporaryStack.isEmpty())
        {
            int currentElement = temporaryStack.pop();

            if(pop & !isPopped & currentElement == minElement){
                isPopped = true;
                continue;
            }
            
            stack.push(currentElement);
        }
        
        return minElement;
    }
    public static int findMin(Stack stack)
    {
        return findMin(stack, false);
    }
    public static int popMin(Stack stack)
    {
        return findMin(stack, true);
    }
    
    public static Stack copyStack(Stack stack)
    {
        Stack tempStack = new Stack(stack.size());
        Stack resultStack = new Stack(stack.size());

        while(!stack.isEmpty())
        {
            int currentElement = stack.pop();

            tempStack.push(currentElement);
        }

        while(!tempStack.isEmpty())
        {
            int currentElement = tempStack.pop();
            
            resultStack.push(currentElement);
            stack.push(currentElement);
        }

        return resultStack;
    }
    
    public static Stack sortStack(Stack stack, boolean isAscending)
    {
        Stack descendingStack = new Stack(stack.size());
        Stack copiedStack = copyStack(stack);   // protect original stack

        // push minimum element to sorted stack ~~ selection sort
        while(!copiedStack.isEmpty())
        {
            int min = popMin(copiedStack);

            descendingStack.push(min);
        }

        // reverse the sorted stack to obtain ascending stack
        if(isAscending)
        {
            while(!descendingStack.isEmpty())
            {
                copiedStack.push(descendingStack.pop());
            }

            return copiedStack;
        } 
        else
        {
            return descendingStack;
        }

    }
    public static Stack sortStack(Stack stack)
    {
        return sortStack(stack, false);
    }
    public static Stack sortStacks(Stack stack1, Stack stack2, boolean isAscending)
    {
        // initialize sorted stack with the size of stacks
        Stack sortedStack = new Stack(stack1.size() + stack2.size());
        
        // to protect original stacks
        Stack copiedStack1 = copyStack(stack1);
        Stack copiedStack2 = copyStack(stack2);

        // fill the sorted stack with elements of stacks
        while(true)
        {
            int element;

            if(!copiedStack1.isEmpty())
            {
                element = copiedStack1.pop();
                sortedStack.push(element);
                continue;
            }
            
            if(!copiedStack2.isEmpty()){
                element = copiedStack2.pop();
                sortedStack.push(element);
                continue;
            }

            break;
        }

        // sort
        sortedStack = sortStack(sortedStack, isAscending);

        return sortedStack;
    }
    public static Stack sortStacks(Stack stack1, Stack stack2)
    {
        return sortStacks(stack1, stack2, false);
    }
    
    public static void main(String[] args) {
        
        // first question region
        Stack stack = new Stack(8);

        stack.push(8);
        stack.push(2);
        stack.push(7);
        stack.push(5);

        stack.display();
        System.out.println("~~ initial stack\n");

        popMin(stack);

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

        Stack sortedStack = sortStacks(stack, stack2);

        sortedStack.display();
        System.out.println("~~ sorted stacks\n");


        // quality region
        stack.display();
        System.out.println("~~ last stack\n");

        stack2.display();
        System.out.println("~~ last stack2\n");
    }

}