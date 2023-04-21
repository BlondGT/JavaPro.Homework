package hm17;

import lombok.*;

@Getter
@Builder
public class HeroBuilder {

    private String name;
    private String gender;
    private String eyeColor;
    private String race;
    private String hairColor;
    private double height;
    private String publisher;
    private String skinColor;
    private String alignment;
    private int weight;
}
