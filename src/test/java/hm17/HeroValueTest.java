package hm17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroValueTest {

    @Test
    void shouldGetValue() {

        var hero = new HeroValue("Name", "male", "blue", null, null, 170, null, null, null, 70);


        assertEquals("male", hero.getGender());
        assertNotEquals("DC", hero.getPublisher());
    }

}