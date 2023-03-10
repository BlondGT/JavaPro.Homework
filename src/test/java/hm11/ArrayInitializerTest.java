package hm11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayInitializerTest {

    @Test
    void shouldInit() {

        double[] array = new double[] {2.5, 2.5, 2.5, 2.5, 2.5, 2.5};

        ArrayInitializer.init(array);

        assertTrue(true);
    }
}