package hm17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroLombokTest {

    @Test
    void shouldGetLombok() {

        HeroLombok hero = new HeroLombok("Name", "male", "blue", null, null, 170, null, null, null, 70);

        assertEquals("blue", hero.getEyeColor());
        assertNotEquals("black", hero.getHairColor());
    }

}