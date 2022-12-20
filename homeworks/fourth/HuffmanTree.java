package homeworks.fourth;

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree(HuffmanList list){
        root = list.popHead();

        while (list.count() > 0){
            HuffmanNode node = list.popHead();

            String parentSymbol = node.symbol + root.symbol;
            float parentFrequency = node.frequency + root.frequency;

            HuffmanNode parentNode = new HuffmanNode(parentSymbol, parentFrequency);

            parentNode.right = node;
            parentNode.left = root;

            root = parentNode;
        }
    }

    public String decode(String encodedSymbol){
        
        String decodedSymbol = "";
        HuffmanNode iterator = root;

        // traverse the encoded symbol
        for (int i=0; i < encodedSymbol.length(); i++){
            char currentCharacter = encodedSymbol.charAt(i);

            if (currentCharacter == '0'){
                iterator = iterator.left;
            }
            else if (currentCharacter == '1'){
                iterator = iterator.right;
            }

            if (iterator.symbol.length() == 1){
                decodedSymbol += iterator.symbol;
                iterator = root;
            }
        }

        return decodedSymbol;
    }

    public String encode(String symbol){

        if (symbol.compareTo("")==0){
            return "";
        }

        String encodedSymbol = "";
        
        // traverse the symbol
        for (int i=0; i < symbol.length(); i++){
            String character = String.valueOf(symbol.charAt(i));
            
            HuffmanNode iterator = root;
            while(iterator != null){
                if (iterator.symbol.compareTo(character)==0){
                    break;
                }
                
                if (iterator.left != null & iterator.left.symbol.contains(character)){
                    encodedSymbol += "0";
                    iterator = iterator.left;
                }
                else{
                    encodedSymbol += "1";
                    iterator = iterator.right;
                }
                
            }
        }

        return encodedSymbol;
    }

    private void inorder(HuffmanNode parent){
        if (parent == null)
            return;

        inorder(parent.left);
        System.out.print(parent.symbol + " ");
        inorder(parent.right);
    }
    public void inorder(){
        inorder(root);
        System.out.println();
    }

    public static void main(String[] args) {
        HuffmanList list = new HuffmanList();
        list.sortedAdd(new HuffmanNode("a", 0.35f));
        list.sortedAdd(new HuffmanNode("b", 0.1f));
        list.sortedAdd(new HuffmanNode("c", 0.2f));
        list.sortedAdd(new HuffmanNode("d", 0.2f));
        list.sortedAdd(new HuffmanNode("_", 0.15f));
        list.display();

        HuffmanTree tree = new HuffmanTree(list);
        tree.inorder();

        System.out.println(tree.encode("c"));
        System.out.println(tree.encode("cdab"));

        System.out.println(tree.decode("001"));
        System.out.println(tree.decode("0010110000"));
    }
}
