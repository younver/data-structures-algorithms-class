package homeworks.huffman;

public class HuffmanList {
    public HuffmanNode head;

    public boolean isEmpty(){
        return head == null;
    }

    public void sortedAdd(HuffmanNode node, HuffmanNode iterator, HuffmanNode prev){
        if (iterator == null){
            prev.next = node;
            return;
        }
        
        if (node.frequency < iterator.frequency){
            node.next = iterator;
            prev.next = node;
            return;
        }
        
        sortedAdd(node, iterator.next, prev.next);
    }
    public void sortedAdd(HuffmanNode node){
        if (head == null){
            head = node;
            return;
        }

        if (head.frequency > node.frequency){
            node.next = head;
            head = node;
            return;
        }

        sortedAdd(node, head.next, head);
    }

    public int count(){
        int count = 0;

        if (head == null){
            return count;
        }

        HuffmanNode iterator = head;
        while (iterator != null){
            count++;
            iterator = iterator.next;
        }

        return count;
    }

    public HuffmanNode popHead(){
        if (isEmpty()){
            return null;
        }

        HuffmanNode node = head;
        head = this.head.next;
        return node;
    }

    public void display(){
        HuffmanNode iterator = head;

        if (head == null){
            System.out.println("~~ huffmanlist is empty");
        }
        
        while(iterator!=null){
            System.out.print(iterator.symbol + " ");
            iterator = iterator.next;
        }
        
        System.out.println();
    }

    public static void main(String[] args) {
        HuffmanList list = new HuffmanList();

        list.sortedAdd(new HuffmanNode("a", 0.5f));
        list.sortedAdd(new HuffmanNode("b", 0.4f));
        list.sortedAdd(new HuffmanNode("c", 0.2f));
        list.sortedAdd(new HuffmanNode("d", 0.3f));
        list.sortedAdd(new HuffmanNode("e", 0.1f));

        list.display();
    }
}
