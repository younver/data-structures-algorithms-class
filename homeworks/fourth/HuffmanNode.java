package homeworks.fourth;

public class HuffmanNode{
    public String symbol;
    public float frequency;
    public HuffmanNode left;
    public HuffmanNode right;
    public HuffmanNode next;

    public HuffmanNode(String symbol, float frequency){
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = this.right = null;
    }
}
