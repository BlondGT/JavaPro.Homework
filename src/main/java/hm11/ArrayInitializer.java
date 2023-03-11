package hm11;

import java.util.Arrays;

public class ArrayInitializer {

    public static void init(double[] array) {

        int size = array.length;
        double[] array1 = Arrays.copyOfRange(array, 0, (size + 1) / 2);
        double[] array2 = Arrays.copyOfRange(array, size - array1.length, size);

        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < array1.length; i++) {
                array[i] = array1[i] * Math.sin(0.2 + i / 5.0)
                        * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);

            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < array2.length; i++) {
                array[i] = array2[i] * Math.sin(0.2 + i / 5.0)
                        * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);

            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted thread");
        }
    }

    public static void main(String[] args) {

        double[] array = new double[10];
        ArrayInitializer.init(array);
        System.out.println(Arrays.toString(array));
    }
}


