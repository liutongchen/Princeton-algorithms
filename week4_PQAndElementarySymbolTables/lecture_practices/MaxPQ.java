/**
 Created by Liutong Chen on 08/30/2018
 */

class BinaryHeap<Key implements Comparable<Key>> {
    private void swim(int k) {
        // binary heap starts from one for convenience
        // a[k]'s parent node is a[k/2]
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    public void insert(Key)

    private boolean less(int i, int j) {
        // TODO: IMPLEMENT LATER
    }

    private void exch(int i, int j) {
        // TODO: IMPLEMENT LATER
    }
}