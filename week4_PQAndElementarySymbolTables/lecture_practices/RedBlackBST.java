/**
 * Created by Liutong Chen on 10/1/2018
 */

// This is left-leaning red-black tree.
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

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color; // color is the line color pointing to a node by its parent
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert isRed(h.left);
        assert isRed(h.right);
        assert !isRed(h);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}
