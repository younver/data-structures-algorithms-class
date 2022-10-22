package exercises;
import exercises.GenericStack;
import structures.queue.Queue;

public class Palindrome {
    
    static boolean isPalindromeOptimized(String inputString){
        
        // iterate through list
        // add first half to first stack
        // in the next half,
        // compare next char with top of stack
        // if same continue
        // else not palindrome

        // add half of the string to stack
        // add rest of the half to queue
        // compare pop n dequeue
        // return false if not same
        
        return false;
    }

    static boolean isPalindrome(String inputString){
        GenericStack<Character> inputStack = new GenericStack<>(Character.class, 10);
        Queue<Character> inputQueue = new Queue<>();

        int n = inputString.length();
        int m = (n / 2) + (n % 2);

        // fill the stack
        for (int i = 0; i < n/2; i++) {
            char c = inputString.charAt(i);
            inputStack.push(c);
        }

        // fill the queue
        for (int i = m; i < n; i++){
            char c = inputString.charAt(i);
            inputQueue.enqueue(c);
        }

        // compare characters
        for (int i = 0; i < n/2; i++) {
            if (inputStack.peek() != inputQueue.tail()){
                return false;
            }

            // remove checked characters
            inputStack.pop();
            inputQueue.dequeue();
        }

        return true;
    }

    public static void main(String[] args) {
        String inputString = "pallap";
        System.out.println(isPalindrome(inputString));
    }
}
