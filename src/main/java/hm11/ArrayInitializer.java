package hm11;

import java.util.Arrays;

public class ArrayInitializer {

    public static void init(double[] array) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < array.length / 2; i++) {
                array[i] = array[i] * Math.sin(0.2 + i / 5.0)
                        * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);

            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = array.length / 2; i < array.length; i++) {
                array[i] = array[i] * Math.sin(0.2 + i / 5.0)
                        * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);

            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted thread");
        }
    }

    public static void main(String[] args) {

        double[] array = new double[10];
        ArrayInitializer.init(array);
        System.out.println(Arrays.toString(array));
    }
}


