package hm16;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class HeroFilters {

    public static double averageHeight(List<Hero> heroes) {
        return heroes.stream()
                .filter(hero -> hero.height() > 0)
                .mapToDouble(Hero::height)
                .average()
                .orElse(0);
    }

    public static String highestHero(List<Hero> heroes) {
        return heroes.stream()
                .max(comparing(Hero::height))
                .map(Hero::name)
                .orElseThrow();
    }

    public static String heaviestHero(List<Hero> heroes) {
        return heroes.stream()
                .max(comparing(Hero::weight))
                .map(Hero::name)
                .orElseThrow();

    }

    public static Map<String, Long> amountOfHeroEachGender(List<Hero> heroes) {
        return heroes.stream()
                .collect(Collectors.groupingBy(Hero::gender, Collectors.counting()));
    }

    public static Map<String, Long> amountOfHeroEachAlignment(List<Hero> heroes) {
        return heroes.stream()
                .collect(Collectors.groupingBy(Hero::alignment, Collectors.counting()));
    }

    public static List<String> mostPopularPublishers(List<Hero> heroes, int size) {
        return heroes.stream()
                .collect(Collectors.groupingBy(Hero::publisher, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(size)
                .map(Map.Entry::getKey)
                .toList();

    }

    public static List<String> mostPopularHairColors(List<Hero> heroes, int size) {
        return heroes.stream()
                .collect(Collectors.groupingBy(Hero::hairColor, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(size)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static List<String> mostPopularEyeColors(List<Hero> heroes, int size) {
        return heroes.stream()
                .collect(Collectors.groupingBy(Hero::eyeColor, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(size)
                .map(Map.Entry::getKey)
                .toList();
    }

}
