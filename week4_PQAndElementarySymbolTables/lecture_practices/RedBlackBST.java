public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public Value get(Key key) {
        Node ref = root;
        while (ref != null) {
            int comp = key.compareTo(ref.key);
            if (comp < 0) {
                ref = ref.left;
            } else if (comp > 0) {
                ref = ref.right;
            } else {
                return ref.val;
            }
        }
        return null;
    }
}
