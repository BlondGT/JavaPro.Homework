package hm16;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HeroLoader {

    public static void main(String[] args) throws IOException {

        String csvFile = "src/main/resources/heroes_information.csv";
        char cvsSplitBy = ';';
        CSVReader reader = new CSVReader(new FileReader(csvFile), cvsSplitBy);
        List<Hero> heroes = new ArrayList<>();

        String[] line;
        boolean firstLine = true;
        while ((line = reader.readNext()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String name = line[0];
            String gender = line[1];
            String eyeColor = line[2];
            String race = line[3];
            String hairColor = line[4];
            double height = Double.parseDouble(line[5]);
            String publisher = line[6];
            String skinColor = line[7];
            String alignment = line[8];
            int weight = Integer.parseInt(line[9]);
            Hero hero = new Hero(name, gender, eyeColor, race, hairColor, height, publisher, skinColor, alignment, weight);
            heroes.add(hero);
        }

        System.out.println("average height: " + HeroFilters.averageHeight(heroes));
        System.out.println("the tallest: " + HeroFilters.highestHero(heroes));
        System.out.println("the heaviest: " + HeroFilters.heaviestHero(heroes));
        System.out.println("gender counts: " + HeroFilters.amountOfHeroEachGender(heroes));
        System.out.println("alignment counts: " + HeroFilters.amountOfHeroEachAlignment(heroes));
        System.out.println("5 most popular publisher: " + HeroFilters.mostPopularPublishers(heroes, 5));
        System.out.println("3 most popular hair color: " + HeroFilters.mostPopularHairColors(heroes, 3));
        System.out.println("the most popular eye color: " + HeroFilters.mostPopularEyeColors(heroes, 1));
    }

}
