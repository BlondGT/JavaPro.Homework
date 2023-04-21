package hm17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroBuilderTest {

    @Test
    void shouldBuilder() {

        var hero = HeroBuilder.builder()
                .name("Name")
                .gender("male")
                .weight(75)
                .build();

       assertEquals("Name", hero.getName());
       assertNotEquals(80, hero.getWeight());
    }
}