package hm21;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter
public class Hero {

    private Long id;
    private String name;
    private String gender;
    private String eyeColor;
    private String race;
    private String hairColor;
    private double height;
    private Long publisher_id;
    private String skinColor;
    private String alignment;
    private int weight;
}
