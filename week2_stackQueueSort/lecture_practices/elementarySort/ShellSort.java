/**
 * Created by Liutong Chen on 08/22/2018
 */

package elementarySort;

/**
 * The worst-case number of compare used by shell sort with 3*h + 1 increments is O(N ** 3/2)
 */

public class ShellSort {
    public static void sort(Comparable[] a)
    {
        int h = 1;
        int N = a.length;

        while (h < N/3) h = 3*h + 1; // Knuth's 3*h + 1 increment sequence

        while (h >= 1) {
            // insertion sort
            for (int i = h; i < N; i++)
            {
                for (int j = i; j >= h && less(a[j], a[j - h]); j-=h)
                {
                    exch(a, j, j-h);
                }
            }
            h = h / 3; // move to next increment
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
