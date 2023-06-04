package hm17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HeroBuilderTest {

    @Test
    void shouldBuilder() {

        var hero = HeroBuilder.builder()
                .name("Name")
                .gender("male")
                .weight(75)
                .build();

        assert hero.getName().equals("Name");
        assertNotEquals(80, hero.getWeight());
    }
}