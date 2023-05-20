package hm26;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "heroes")
public class HibernateHeroes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    @Column(name = "eye_color")
    private String eyeColor;
    private String race;
    @Column(name = "hair_color")
    private String hairColor;
    private double height;
    @Column(name = "skin_color")
    private String skinColor;
    private String alignment;
    private int weight;
}
