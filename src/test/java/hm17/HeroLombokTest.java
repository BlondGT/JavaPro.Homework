package hm17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HeroLombokTest {

    @Test
    void shouldGetLombok() {

        HeroLombok hero = new HeroLombok();
        hero.setName("Name");
        hero.setEyeColor("blue");
        hero.setHairColor("blond");

        assert hero.getEyeColor().equals("blue");
        assertNotEquals("black", hero.getHairColor());
    }

}