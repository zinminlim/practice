package home.zin.tree;

public class FibonacciTree {
    /**
     * This method build object structure based on fibonacci sequence
     * f(n) = f(n-1) + f(n-2)
     */

    public Node buildFibonacciTree(int num){
        if (num < 1){
            return null;
        }else if (num == 1){
            return new Node(num,null, null);
        } else {
            return new Node(num, buildFibonacciTree(num - 1), buildFibonacciTree(num - 2));
        }
    }


    public static void main (String[] args){
        // =============================
        // fibonacci
        // =============================
        FibonacciTree fibBT = new FibonacciTree();
        Node fibNode = fibBT.buildFibonacciTree(4);
       // TreeUtils.printTree(fibNode);
        BTreePrinter.printNode(fibNode);
        // =============================

    }
}
