class Node {
    private final Integer exponent;
    private Integer value;
    private Node nextNode;

    public Node(Integer value, Integer exponent, Node nextNode) {
        this.exponent = exponent;
        this.value = value;
        this.nextNode = nextNode;
    }

    public int getExponent() {
        return exponent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "exponent=" + exponent +
                ", value=" + value +
                '}';
    }
}
