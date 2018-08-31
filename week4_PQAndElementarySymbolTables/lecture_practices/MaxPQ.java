/**
 Created by Liutong Chen on 08/30/2018
 */

class MaxPQ<Key extends Comparable<Key>> {
    private Key[] priorityQueue;
    private int num;


    public void insert(Key key) {
        priorityQueue[++num] = key; // increment before insert to make it start from one
        swim(num);
    }

    public void sink(int k) {
        while (2 * k <= num) {
            int biggerChild = 2 * k;
            if (biggerChild < num && less(biggerChild, biggerChild + 1)) biggerChild++;
            if (!less(k, biggerChild)) break;
            exch(k, biggerChild);
            k = biggerChild;
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