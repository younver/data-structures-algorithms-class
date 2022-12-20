package structures.trees;

import structures.node.TNode;

public class BinarySearchTree<T extends Comparable> {
    TNode<T> root;

    public void insert(T value){
        // empty case
        if (root == null){
            root = new TNode<T>(value);
            return;
        }

        // iterate
        TNode<T> iterator = root;
        while (iterator != null){
            // if greater or equal to
            if (value.compareTo(iterator.value) >= 0){
                if (iterator.right == null){
                    iterator.right = new TNode<T>(value);
                    return;
                }
                iterator = iterator.right;
            }
            
            // else
            if (iterator.left == null){
                iterator.left = new TNode<T>(value);
                return;
            }
            iterator = iterator.left;
        
        }
    }

    private TNode<T> Rinsert(T value, TNode<T> root){
        if (root == null)
            return new TNode<T>(value);
        if (value.compareTo(root.value) >= 0){
            root.left = Rinsert(value, root.left);
        }
        else{
            root.right = Rinsert(value, root.right);
        }

        return root;
    }
    public void Rinsert(T value){
        root = Rinsert(value, root);
    }

    /*
    private void Rinsert(T value, TNode<T> iterator){
        if (value.compareTo(iterator.value) >= 0){
            if (iterator.right == null){
                iterator.right = new TNode<T>(value);
                return;
            }
            Rinsert(value, iterator.right);
        }
        else{
            if (iterator.left == null){
                iterator.left = new TNode<T>(value);
                return;
            }
            Rinsert(value, iterator.left);
        }
    }
    public void Rinsert(T value){
        if (root == null){
            root = new TNode<T>(value);
            return;
        }
        Rinsert(value, root);
    }

    */

    private void inorder(TNode<T> root){
        if (root == null)
            return;
        
        inorder(root.left);
        System.out.print(root + " ");
        inorder(root.right);
    }
    public void inorder(){
        inorder(root);
        System.out.println();
    }
    
    private void preorder(TNode<T> root){
        if (root == null)
            return;
        
        System.out.print(root + " ");
        preorder(root.left);
        preorder(root.right);
    }
    public void preorder(){
        preorder(root);
        System.out.println();
    }

    private void postorder(TNode<T> root){
        if (root == null)
            return;
        
        postorder(root.left);
        postorder(root.right);
        System.out.print(root + " ");
    }
    public void postorder(){
        postorder(root);
        System.out.println();
    }

    // TODO: fix
    private boolean Rsearch(TNode<T> iterator, T value){
        if (iterator == null)
            return false;

        else if (value.compareTo(iterator.value) == 0)
            return true;
        
        else if (value.compareTo(iterator.value) == 1)
            return Rsearch(iterator.right, value);

        else
            return Rsearch(iterator.left, value);
    }
    public boolean Rsearch(T value){
        return Rsearch(root, value);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.Rinsert(10);
        tree.Rinsert(5);
        tree.Rinsert(12);
        tree.Rinsert(16);

        tree.inorder();
        tree.preorder();
        tree.postorder();

        System.out.println(tree.Rsearch(12));
        System.out.println(tree.Rsearch(83));
    }
}
