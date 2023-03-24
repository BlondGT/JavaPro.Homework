package hm13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



public class SimpleCalculator {

    public static CompletableFuture<Integer> squareSum(int first, int second) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return first * second;})
                .thenApply(sum -> sum * 2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = squareSum(2, 2);

        System.out.println("Sum: " + future.get());
    }
}
