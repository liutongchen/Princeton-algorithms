/**
 * Created by Liutong Chen on 09/19/2018
 */

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
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

    public void delete(Key key) {

    }

    public Iterable<Key> iterator() {

    }
}
