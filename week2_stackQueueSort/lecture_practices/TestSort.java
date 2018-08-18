public class TestSort {
    public static void main(String[] args) {
        SelectionSort testSelectionSort = new SelectionSort();
        System.out.println("-------SELECTION SORT------");
        Comparable[] arr = {5, 2, 9, 4};
        for (int i = 0; i < arr.length; i++) {
            System.out.println("BEFORE: " + arr[i]);
        }
        long selectionSortStartTime = System.nanoTime();
        testSelectionSort.sort(arr);
        long selectionSortEndTime = System.nanoTime();
        System.out.println(":::::TIME::::: " + (selectionSortEndTime - selectionSortStartTime));
        for (int i = 0; i < arr.length; i++) {
            System.out.println("AFTER: " + arr[i]);
        }
    }
}
