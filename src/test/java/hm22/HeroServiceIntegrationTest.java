package hm22;

import hm21.Hero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroServiceIntegrationTest {

    private final HeroService target = HeroFabric.createDummyService(List.of(
            Hero.builder().id(1L).build(),
            Hero.builder().id(2L).build(),
            Hero.builder().id(3L).build()
    ));

    @Test
    void shouldReturnListOfHeroes() {
        var heroDtos = target.getHeroes();

        assertEquals(3, heroDtos.size());
    }
}
