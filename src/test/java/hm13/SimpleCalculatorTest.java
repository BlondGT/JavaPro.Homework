package hm13;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCalculatorTest {

    @Test
    void shouldSquareSum() throws InterruptedException, ExecutionException {

        assertEquals(18, SimpleCalculator.squareSum(3, 3).get());
    }
}
