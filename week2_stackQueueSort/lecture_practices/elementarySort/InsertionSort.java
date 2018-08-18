/**
 * Create by Liutong Chen on 08/17/2018
 */

/**
 * The following implementation takes T((1/4) * n^2) -- quadratic but twice as fast as selection sort.
 */
public class InsertionSort {

    /**
     * Left of i are always sorted;
     * j is the swap pointer.
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j-1);
                }
                else break;
            }
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
