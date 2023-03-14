package hm11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayInitializerTest {

    @Test
    void shouldInit() {

        double[] array = new double[] {2.5, 2.5, 2.5, 2.5};

        ArrayInitializer.init(array);

        assertArrayEquals(new double[] {0.44834755681220173, 0.5573946211285253,
                0.198020025641502, -0.40393964646934954}, array);
    }
}