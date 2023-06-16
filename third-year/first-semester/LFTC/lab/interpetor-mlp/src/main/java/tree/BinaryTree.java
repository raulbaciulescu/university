package tree;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private int contor = 0;
    private Node root;

    /**
     * add a new node in tree
     * @param current a new node
     * @param value value of node
     * @return the node if it's added successfully
     */
    private Node addRecursive(Node current, String value) {
        if (current == null) {
            contor++;
            return new Node(value, contor);
        }

        if (value.compareTo(current.value) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    /**
     * traverse a tree and print in file its nodes
     * @param outputStream output where the tree is printed
     */
    public void traverseLevelOrder(FileOutputStream outputStream) throws IOException {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            outputStream.write((node.contor + " value: " + node.value + " st: " + node.left + " dr: " + node.right + "\n").getBytes());

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    /**
     * add a new node in tree
     * @param value value of node
     */
    public void add(String value) {
        root = addRecursive(root, value);
    }

    /**
     * check if a node it is in tree
     * @param current current node in the tree
     * @param value the value we check
     * @return contor the contor of the found node
     */
    private int containsNodeRecursive(Node current, String value) {
        if (current == null) {
            return -1;
        }
        if (value.compareTo(current.value) == 0) {
            return current.contor;
        }
        return value.compareTo(current.value) < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public int getContorByValue(String value) {
        return containsNodeRecursive(root, value);
    }
}
