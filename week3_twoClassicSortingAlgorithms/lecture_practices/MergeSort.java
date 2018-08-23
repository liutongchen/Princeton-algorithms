/**
 * Created by Liutong Chen on 08/22/2018
 */

/**
 * O(N * logN)
 * @param <Item>
 */
class MergeSort<Item> {

    /**
     * Check if an array is sorted
     */
    private static boolean isSorted(Comparable[] a, int begin, int end) {
        return true; // TODO: IMPLEMENT THIS LATER
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
     * Merge two sorted sub arrays a[low...mid] and a[mid+1...high]
     */
    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        assert isSorted(a, low, mid);
        assert isSorted(a, mid+1, high);

        // copy a into aux
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > high) aux[k] = a[i++];
            else if (less(a[i], a[j])) aux[k] = a[i++];
            else aux[k] = a[j++];
        }

        assert isSorted(a, low, high);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
        // TODO: USE INSERTION SORT FOR ARRAYS OF SMALLER SIZE AFTER IT'S CUT IN HALF
        if (high <= low) return;
        int mid = (low + high) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid+1, high);
        if (!(less(mid + 1, mid))) return;
        merge(a, aux, low, mid, high);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
}