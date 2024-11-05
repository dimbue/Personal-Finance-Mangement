import java.util.ArrayList;
import java.util.Comparator;

public class SortingUtility<T> {
    public void sort(ArrayList<T> records, Comparator<T> comparator) {
        // Example: Using mergesort for demonstration
        mergeSort(records, comparator);
    }

    private void mergeSort(ArrayList<T> records, Comparator<T> comparator) {
        if (records.size() < 2) return;

        int mid = records.size() / 2;
        ArrayList<T> left = new ArrayList<>(records.subList(0, mid));
        ArrayList<T> right = new ArrayList<>(records.subList(mid, records.size()));

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        merge(records, left, right, comparator);
    }

    private void merge(ArrayList<T> records, ArrayList<T> left, ArrayList<T> right, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                records.set(k++, left.get(i++));
            } else {
                records.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) records.set(k++, left.get(i++));
        while (j < right.size()) records.set(k++, right.get(j++));
    }
}
