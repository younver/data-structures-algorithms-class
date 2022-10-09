public class InfixPrefix {
    public static void main(String[] args) {
        String infixExpression = "a/b*(c+(d-e))";
        GenericStack<Character> prefixStack = new GenericStack<>(char.class, 20);
        GenericStack<Character> operatorStack = new GenericStack<>(char.class, 20);

        
        for (int i = 0; i < infixExpression.length(); i++) {
            char character = infixExpression.charAt(i);
            
            switch (character) {
                case '+' | '-' | '*' | '/' | '(' | ')' :
                    // operator
                    operatorStack.push(character);
                    break;
                default:
                    // number
                    prefixStack.push(character);
                    break;
            }

            
            
        }


        // System.out.println(prefixExpression);
    }
}
