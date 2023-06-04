package hm16;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hm16.HeroFilters.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroFiltersTest {

    List<Hero> heroes = List.of(
            new Hero("A", "male", "blue", "human", "black", 190.0, "SPT", "white", "bad", 80),
            new Hero("B", "female", "grey", "alien", "blond", 165.0, "Marvel", "black", "good", 50),
            new Hero("C", "female", "green", "human", "no hair", 90.0, "George", "white", "bad", 88),
            new Hero("D", "male", "blue", "human", "black", 173.0, "Dark Horse", "white", "neutral", -99),
            new Hero("E", "male", "grey", "alien", "black", 187.0, "DC", "white", "bad", -99),
            new Hero("F", "female", "red", "human", "pink", 136.0, "George", "violet", "bad", 45),
            new Hero("G", "female", "blue", "human", "black", 164.0, "SPT", "white", "bad", 74),
            new Hero("K", "male", "grey", "alien", "no hair", 185.0, "DC", "white", "good", 67),
            new Hero("L", "male", "green", "alien", "orange", 193.0, "NBC", "black", "good", 90),
            new Hero("M", "male", "blue", "human", "blond", 197.0, "DC", "black", "good", 180),
            new Hero("N", "female", "grey", "alien", "black", 210.0, "Marvel", "white", "neutral", 101),
            new Hero("O", "female", "--", "human", "blond", 201.0, "NBC", "white", "bad", 124),
            new Hero("P", "female", "blue", "alien", "blond", 190.0, "DC", "white", "bad", 150),
            new Hero("R", "male", "green", "human", "black", 160.0, "Marvel", "white", "good", 207)
    );

    @Test
    void shouldFindAverageHeight() {

        assertEquals(174.35714285714286, averageHeight(heroes));
    }

    @Test
    void shouldFindHighestHero() {

        assertEquals("N", highestHero(heroes));
    }

    @Test
    void shouldFindHeaviestHero() {

        assertEquals("R", HeroFilters.heaviestHero(heroes));
    }

    @Test
    void shouldFindAmountOfHeroEachGender() {

        Map<String, Long> expected = new HashMap<>();
        expected.put("male", 7L);
        expected.put("female", 7L);

        assertEquals(expected, amountOfHeroEachGender(heroes));
    }

    @Test
    void shouldFindAmountOfHeroEachAlignment() {

        Map<String, Long> expected = new HashMap<>();
        expected.put("bad", 7L);
        expected.put("good", 5L);
        expected.put("neutral", 2L);

        assertEquals(expected, HeroFilters.amountOfHeroEachAlignment(heroes));
    }

    @Test
    void shouldFindMostPopularPublishers() {

        assertEquals(List.of("DC", "Marvel", "George", "SPT", "NBC"), mostPopularPublishers(heroes, 5));
    }

    @Test
    void shouldFindMostPopularHairColors() {

        assertEquals(List.of("black", "blond", "no hair"), mostPopularHairColors(heroes, 3));
    }

    @Test
    void shouldFindMostPopularEyeColors() {

        assertEquals(List.of("blue"), mostPopularEyeColors(heroes, 1));
    }
}