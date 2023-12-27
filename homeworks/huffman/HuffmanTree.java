package homeworks.huffman;

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree(HuffmanList list){
        HuffmanNode currentRoot;
        while (true) {
            HuffmanNode leftNode = list.popHead();
            HuffmanNode rightNode = list.popHead();
            currentRoot = new HuffmanNode(leftNode.symbol + rightNode.symbol, leftNode.frequency + rightNode.frequency);
            currentRoot.left = leftNode;
            currentRoot.right = rightNode;
            if (list.isEmpty())
                break;
            list.sortedAdd(currentRoot);
        }
        root = currentRoot;
    }

    public String decode(String encodedSymbol){
        String decodedSymbol = "";
        HuffmanNode iterator = root;
        for (char c : encodedSymbol.toCharArray()){
            if (c == '0'){
                iterator = iterator.left;
            }
            else {
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
        String encodedSymbol = "";
        for (char c : symbol.toCharArray()){
            String character = String.valueOf(c);

            HuffmanNode iterator = root;
            while(iterator != null){
                if (iterator.symbol.length() == 1)
                    break;
                if (iterator.left != null && iterator.left.symbol.contains(character)){
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

        System.out.println(tree.decode("000110010111"));
        System.out.println(tree.decode("0010110000"));
    }
}
