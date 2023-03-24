package hm13;


import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class ArrayInitializerForkJoin extends RecursiveAction {

        private final double[] array;
        private final int start;
        private final int end;

        public ArrayInitializerForkJoin(double[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {

            if ((end - start) < 20) {
                for (int i = start; i < end; i++) {
                    array[i] = array[i] * Math.sin(0.2 + i / 5.0)
                            * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);
                }
            } else {
                int middle = (start + end) / 2;
                ArrayInitializerForkJoin left = new ArrayInitializerForkJoin(array, start, middle);
                ArrayInitializerForkJoin right = new ArrayInitializerForkJoin(array, middle, end);

                left.fork();
                right.fork();

                left.join();
                right.join();
            }

        }

        public static void init(double[] array) {

                ForkJoinPool pool = new ForkJoinPool();
                pool.invoke(new ArrayInitializerForkJoin(array, 0, array.length));
        }

        public static void main(String[] args) {

            double[] array = new double[100];
            Arrays.fill(array, 3);
            init(array);
            System.out.println(Arrays.toString(array));
    }
}


