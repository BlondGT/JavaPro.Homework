package hm12;

import java.util.Arrays;
import java.util.Objects;

public class GenericArray<T> implements  GenericHillelList<T>{

    private T[] arrays = (T[]) new Object[0];

    public static void main(String[] args) {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("el1");
        genericArray.add("el2");
        genericArray.add("el3");
        genericArray.add("el4");
        genericArray.add("el5");
        genericArray.add("el1");
        genericArray.remove(3);
        System.out.println(Arrays.toString(genericArray.toArray()));

    }

    @SuppressWarnings("unchecked")
    public T[] append(T[] arrays, T item) {
        T[] arr = (T[]) new Object[arrays.length + 1];
        System.arraycopy(arrays, 0, arr, 0, arrays.length);
        arr[arrays.length] = item;
        return arr;
    }

    public static<T> T[] removeElement(T[] arrays, int index) {

        T[] arrDestination = (T[]) new Object[arrays.length - 1];
        int remainingElement =arrays.length - (index + 1);
        System.arraycopy(arrays, 0, arrDestination, 0, index);
        System.arraycopy(arrays, index + 1, arrDestination, index, remainingElement);
        return arrDestination;
    }


    public static<T> int findIndexOf(T[] arr, T item) {

        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], item)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void add(T item) {

        arrays = append(arrays, item);
    }

    @Override
    public T remove(int index) {
        T removeElement = arrays[index];
        arrays = removeElement (arrays, index);
        return removeElement;
    }

    @Override
    public boolean contains(T item) {

        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(T item) {

        return findIndexOf(arrays,item);
    }

    @Override
    public int size() {

        return arrays.length;
    }

    @Override
    public T get(int index) {

        return arrays[index];
    }

    @Override
    public Object[] toArray() {

        return arrays;
    }

    @Override
    public T[] toArray(T[] type) {

        return arrays;
    }
}
