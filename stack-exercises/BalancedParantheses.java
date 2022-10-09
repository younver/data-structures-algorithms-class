public class BalancedParantheses {

    public static int indexInList(String toFind, String[] list){
        for(int i=0; i<list.length; i++){
            if(list[i] == toFind){
                return i;
            }
        }
        return -1;
    }

    public static boolean isBalanced(String[] paranthesis){
        GenericStack<String> stack = new GenericStack<>(String.class, 5);
        String[] openParanthesis = {"(", "{", "["};
        String[] closeParanthesis = {")", "}", "]"};

        if(paranthesis.length % 2 == 1){
            return false;
        }
        
        for(int i=0; i<paranthesis.length; i++){
        
            for(int j=0; j<3; j++){
                if(paranthesis[i] == openParanthesis[j]){
                    stack.push(paranthesis[i]);
                }
    
                if(paranthesis[i] == closeParanthesis[j]){
                    if (j != indexInList(stack.pop(), openParanthesis)){
                        return false;
                    }
    
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] paranthesis = {"(", "[","]", "{", "(", ")", "}", ")"};
    
        System.out.println(isBalanced(paranthesis));
    }
}
