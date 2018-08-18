/**
 * Created by Liutong Chen on 08/17/2018
 */

/**
 * Selection sort is the first elementary sort introduced by the course.
 * The following implementation takes T(n^2 / 2) -- quadratic
 */
public class SelectionSort {
    public static void sort(Comparable[] a) {
        int min;

        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i+1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
