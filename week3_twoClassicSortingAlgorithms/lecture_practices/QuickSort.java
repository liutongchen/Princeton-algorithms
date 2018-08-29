/**
 * Created by Liutong Chen on 08/29/2018
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * Best case: divide everything in half -- O(N * lgN)
 * Worst case: everything is exactly in order -- O(~ 1/2 * N ^ 2)
 * Average case: O(~ 2N * lnN) / O(1.39N * lgN) (number of compares), O(~1/3N * lnN) (number of exchanges)
 * => 39% more compares than MergeSort but faster because of less data movement;
 * => takes fewer space than MergeSort;
 * => stable sort is harder to implement for QuickSort than MergeSort
 */

public class QuickSort {
    /**
     * Find the partition index j where left of a[j] are all smaller than a[j] and right are all greater than it.
     * @param a
     * @param low
     * @param high
     * @return
     */
    private static int partition(Comparable[] a, int low, int high) {
        int i = low;
        int j = high + 1;
        while (true) {
            while(less(a[++i], a[low])) {
                if (i == high) break;
            }

            while (less(a[low], a[--j])) {
                if (j == low) break;
            }

            if (i > j) break;
            exch(a, i, j);
        }
        exch(a, j, low);
        return j;
    }

    /**
     * Compare v and w and return true if v is smaller than w, otherwise return false
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Exchange a[i] with a[j]
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Recursively sort array
     * @param a
     */
    private static void sort(Comparable[] a, int low, int high) {
        if (low >= high) return;
        int j = partition(a, low, high);
        sort(a, 0, j - 1);
        sort(a, j + 1, high);
    }

    /**
     * Shuffle array before sorting it for performance guarantee.
     * @param a
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    /**
     * Select the Kth smallest element in a
     */
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int low = 0;
        int high = a.length - 1;
        int j = partition(a, low, high);
        while (low < high) {
            if (j < k) {
                j = partition(a, j + 1, high);
            } else if (j > k) {
                j = partition(a, low, j - 1);
            } else {
                return a[k];
            }
        }
        return a[k];
    }
}
