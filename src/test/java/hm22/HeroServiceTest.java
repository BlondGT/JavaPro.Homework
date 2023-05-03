package hm22;

import hm21.Hero;
import hm21.HeroDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroServiceTest {


    @Test
    void getHeroes() {

        var mockHeroDao = Mockito.mock(HeroDao.class);
        Mockito.when(mockHeroDao.findAll()).thenReturn(List.of(
                Hero.builder().name("Superman").build(),
                Hero.builder().name("Batman").build(),
                Hero.builder().name("Spider-man").build()
        ));

        var mockHeroMovieService = Mockito.mock(HeroMovieService.class);
        Mockito.when(mockHeroMovieService.getPlayedIn("Superman")).thenReturn(List.of("qwe", "rty"));
        Mockito.when(mockHeroMovieService.getPlayedIn("Batman")).thenReturn(List.of("asd", "fgh"));
        Mockito.when(mockHeroMovieService.getPlayedIn("Spider-man")).thenReturn(List.of("zxc", "vbn"));


        HeroService target = new HeroService(mockHeroDao,mockHeroMovieService);
        var heroes = target.getHeroes();

        List<HeroDto> expected = List.of(
                new HeroDto.Builder().withName("Superman").withMovies(List.of("qwe", "rty")).build(),
                new HeroDto.Builder().withName("Batman").withMovies(List.of("asd", "fgh")).build(),
                new HeroDto.Builder().withName("Spider-man").withMovies(List.of("zxc", "vbn")).build());


        assertEquals(expected.size(), heroes.size());
    }
}