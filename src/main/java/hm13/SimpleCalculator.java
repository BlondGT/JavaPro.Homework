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
            .thenCombine(CompletableFuture.supplyAsync(() -> {
                return first * second;
                }),
                    Integer::sum)
                .thenApply((sum) -> {
                    return sum;
                });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = squareSum(2, 2);

        System.out.println("Sum: " + future.get());
    }
}
