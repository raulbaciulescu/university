package tree;

class Node {
    protected int contor;
    protected String value;
    protected Node left;
    protected Node right;

    public Node(String value, int contor) {
        this.contor = contor;
        this.value = value;
        right = null;
        left = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "contor=" + contor +
                ", value='" + value + '\'' +
                '}';
    }
}
