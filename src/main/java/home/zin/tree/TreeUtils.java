package home.zin.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {
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
}
