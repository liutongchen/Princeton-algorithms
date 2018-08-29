/**
 * Created by Liutong Chen on 08/29/2018
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
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Exchange a[i] with a[j]
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
