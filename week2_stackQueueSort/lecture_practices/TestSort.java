/**
 * Created by Liutong Chen on 08/17/2018
 */


/**
 * Test results:
 * Insertion sort works faster than selection sort in partially sorted array;
 * Selection sort works faster than selection sort in an array of descending order;
 */
public class TestSort {
    private static final Comparable[] randomArr = {5, 7, 3, 2, 4, 10, 1};
    private static final Comparable[] partiallySortedArr = {2, 3, 4, 9, 5, 6, 7};
    private static final Comparable[] descendingArr = {7, 6, 5, 4, 3, 2, 1};

    private static Comparable[] copyArray(Comparable[] a) {
        int len = a.length;
        Comparable[] copiedArr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            copiedArr[i] = a[i];
        }
        return copiedArr;
    }

    private static void testDifferentSort(Comparable[] inp) {
        SelectionSort testSelectionSort = new SelectionSort();
        InsertionSort testInsertionSort = new InsertionSort();

        System.out.println("-------SELECTION SORT------");
        Comparable[] SSArrayCopy = copyArray(inp);
        long selectionSortStartTime = System.nanoTime();
        testSelectionSort.sort(SSArrayCopy);
        long selectionSortEndTime = System.nanoTime();
        System.out.println(":::::TIME::::: " + (selectionSortEndTime - selectionSortStartTime));
        for (int i = 0; i <  SSArrayCopy.length; i++) {
            System.out.println("AFTER: " +  SSArrayCopy[i]);
        }

        System.out.println("-------INSERTION SORT------");
        Comparable[] ISArrayCopy = copyArray(inp);
        long insertionSortStartTime = System.nanoTime();
        testInsertionSort.sort(ISArrayCopy);
        long insertionSortEndTime = System.nanoTime();
        System.out.println(":::::TIME::::: " + (insertionSortEndTime - insertionSortStartTime));
        for (int i = 0; i < ISArrayCopy.length; i++) {
            System.out.println("AFTER: " + ISArrayCopy[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=======Random Array=========");
        testDifferentSort(randomArr);

        System.out.println("=======Partially Sorted Array=========");
        testDifferentSort(partiallySortedArr);

        System.out.println("=======Descending Array=========");
        testDifferentSort(descendingArr);
    }
}
