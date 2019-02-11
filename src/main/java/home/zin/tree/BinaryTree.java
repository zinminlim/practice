package home.zin.tree;

public class BinaryTree {

    /**
     * Searching on Binary Tree.
     */

    public Node search(Node root,int n){
        if (root == null){
            return null;
        }
        Node currentNode = root;
        boolean done = false;
        while(!done){
            if (currentNode == null){
                done = true;
            } else {
                if (currentNode.value == n) {
                    return currentNode;
                } else if (n < currentNode.value) {
                    currentNode = currentNode.left;
                } else if (n > currentNode.value) {
                    currentNode = currentNode.right;
                }
            }
        }
        return null;
    }

    /**
     * Insert Binary tree node.
     */
    public Node insertNode(Node root, int n){
        if (root == null){
            root = new Node(n,null,null);
        }
        Node currentNode = root;
        boolean done = false;

        while(!done){
            if (n < currentNode.value){
                if (currentNode.left == null){
                    Node node = new Node(n,null,null);
                    currentNode.left = node;
                    done = true;
                }else{
                    currentNode = currentNode.left;
                }
            }else if (n > currentNode.value){
                if (currentNode.right == null){
                    Node node = new Node(n,null,null);
                    currentNode.right = node;
                    done = true;
                }else{
                    currentNode = currentNode.right;
                }
            }else{
                done = true;
            }
        }
        return root;
    }


    public static void main (String[] args){
        // =============================
        // create tree
        // =============================
        BinaryTree tree = new BinaryTree();
        Node root = tree.insertNode(null,5);
        tree.insertNode(root,8);
        tree.insertNode(root,3);
        tree.insertNode(root,4);
        tree.insertNode(root,1);
        tree.insertNode(root,2);
        tree.insertNode(root,7);
        tree.insertNode(root,20);
        BTreePrinter.printNode(root);
        // =============================

        Node node2 = tree.search(root,2);
        BTreePrinter.printNode(node2);
        Node nodeNull = tree.search(root, 6);
        BTreePrinter.printNode(nodeNull);
    }
}
