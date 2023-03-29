package hm15;

import org.junit.jupiter.api.Test;

import static hm15.QuickSort.quickSort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {


    @Test
    void shouldQuickSort() {

        int[] array = {9, 4, 1, 5, 10, 2};
        quickSort(array, 0, array.length - 1);

        assertArrayEquals(new int[]{1, 2, 4, 5, 9, 10}, array);

    }
}