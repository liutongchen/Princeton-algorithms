/**
 Created by Liutong Chen on 08/30/2018
 */

class MaxPQ<Key extends Comparable<Key>> {
    private Key[] priorityQueue;
    private int num;

    public MaxPQ(int capacity) {
        priorityQueue = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public void insert(Key key) {
        priorityQueue[++num] = key; // increment before insert to make it start from one
        swim(num);
    }

    public void sink(int k) {
        while (2 * k <= num) {
            int j = 2 * k;
            if (j < num && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public Key delMax() {
        // TODO: THINK HOW TO REMOVE THE MINIMUM?
        Key max = priorityQueue[1];
        exch(1, num--);
        priorityQueue[num+1] = null;
        sink(1);
        return max;
    }

    /**
     * Heap sort: in-place merge and O(NlogN) worst-case guarantee.
     * Heap sort has its disadvantages as well:
     * 1. Inner loop does more things than quicksort's
     * 2. Makes poor use of cache memory
     * 3. Not stable (long-distance exchanges)
     * @param pq
     */
    public  void sort(Comparable[] pq) {
        int n = pq.length;
        // rearrange the array into heap order: bottom-up strategy
        for (int k = n / 2; k <= 1; k--) {
            sink(k);
        }
        while(n > 1) {
            // sort down so that the array is in ascending order
            exch(1, n--);
            sink(1);
        }

    }

    private void swim(int k) {
        // binary heap starts from one for convenience
        // a[k]'s parent node is a[k/2]
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private boolean less(int i, int j) {
        return priorityQueue[i].compareTo(priorityQueue[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = swap;
    }
}