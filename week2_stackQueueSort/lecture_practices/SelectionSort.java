import edu.princeton.cs.algs4.Selection;

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

    public static void main(String[] args) {
        SelectionSort testSelectionSort = new SelectionSort();
        Comparable[] arr = {5, 2, 9, 4};
        for (int i = 0; i < arr.length; i++) {
            System.out.println("BEFORE: " + arr[i]);
        }
        testSelectionSort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("AFTER: " + arr[i]);
        }
    }
}
