package home.zin;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {


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

    public static class Node {
        int value = 0;
        Node left,right;
        Node(int value,Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static class PrintNode {
         Node node;
         int level;
         PrintNode(Node node, int level){
             this.node = node;
             this.level = level;
         }
    }

    /**
     * This method print the tree.
     */
    public static void printTree(Node n){
        Queue<PrintNode> queue = new LinkedList<>();
        queue.add(new PrintNode(n, 0));
        while(!queue.isEmpty()){
            PrintNode current = queue.remove();
            if (current != null && current.node != null) {
                System.out.println("level:"+current.level + "=>" +current.node.value);
                queue.add(new PrintNode(current.node.left, current.level + 1));
                queue.add(new PrintNode(current.node.right, current.level + 1));
            }
        }
    }


    public static void main (String[] args){
        // =============================
        // fibonacci
        // =============================
        BinaryTree fibBT = new BinaryTree();
        Node fibNode = fibBT.buildFibonacciTree(4);
        fibBT.printTree(fibNode);
        // =============================

    }
}
