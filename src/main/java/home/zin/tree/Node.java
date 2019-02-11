package home.zin.tree;

public class Node {
    int value = 0;
    Node left, right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
