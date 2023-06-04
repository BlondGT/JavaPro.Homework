package hm15;

import java.util.Arrays;

public class QuickSort {

    private static void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int partition(int[] array, int start, int end) {

        int pivot = array[end];
        int pIndex = start;

        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                swap(array, i, pIndex);
                pIndex++;
            }
        }
        swap(array, end, pIndex);
        return pIndex;
    }

    public static void quickSort(int[] array, int start, int end) {

        if (start >= end) {
            return;
        }
        int pivot = partition(array, start, end);

        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    public static void main(String[] args) {

        int[] array = {3, 8, 1, 0, -5, 12, -1, 6};

        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }
}
