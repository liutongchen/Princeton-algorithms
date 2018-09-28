import edu.princeton.cs.algs4.Queue;

/**
 * Created by Liutong Chen on 09/19/2018
 */

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
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
        x.count = 1 + size(x.left) + size(x.right);
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

    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node node) {
        if (node.left == null)  return node.right;
        node.left = delMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(Key key) {
        // Hibbard deletion
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp < 0) node.left = delete(node.left, key);
        else if (comp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node temp = node;
            node = min(node.right);
            node.left = temp.left;
            node.right = delMin(temp.right);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Iterable<Key> iterator() {
        Queue<Key> q = new Queue<Key>();
        inOrder(q, root);
        return q;
    }

    /**
     * In-order traversal that takes O(n) space and time.
     * @param q
     * @param node
     */
    private void inOrder(Queue<Key> q, Node node) {
        if (node == null) return;
        inOrder(q, node.left);
        q.enqueue(node.key);
        inOrder(q, node.right);
    }

    /**
     * In-order traversal that takes O(1) space and O(n) time.
     * Ref: https://www.youtube.com/watch?v=wGXB9OWhPTg
     * @param root
     */
    public void morrisTraversal(Node root) {
        Node cur = root;
        while (cur != null) {
            if (cur.left == null) {
              visit(cur);
              cur = cur.right;
            } else {
                // find the rightmost node on the left subtree
                Node rightMost = cur.left;
                while (rightMost.right != null && rightMost.right != cur) {
                    rightMost = rightMost.right;
                }
                if (rightMost == null) {
                    rightMost.right = cur;
                    //visit(cur);
                    cur = cur.left;
                } else {
                    // left are all visited, move to the right after visit the current
                    rightMost.right = null;
                    visit(cur);
                    cur = cur.right;
                }
            }
        }
    }

    private void visit(Node node) {
        System.out.println(node.key);
    }

    /**
     * Find the floor(G): A key that is the biggest number smaller than G
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node root, Key key) {
        if (root == null) return null;
        int comp = key.compareTo(root.key);
        if (comp == 0) return root;
        else if (comp < 0) {
            return floor(root.left, key); }
        Node biggerNode = floor(root.right, key);
        if (biggerNode != null) return biggerNode;
        else return root;
    }

    /**
     * rank(k): count keys that are smaller than k
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int comp = key.compareTo(node.key);
        if (comp == 0) return size(node.left);
        else if (comp < 0) {
            return rank(node.left, key);
        } else {
            return 1 + size(node.left) + rank(node.right, key);
        }
    }
}
