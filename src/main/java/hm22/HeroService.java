package hm22;

import hm21.HeroDao;

import java.util.List;

public class HeroService {

    private final HeroDao dao;
    private final HeroMovieService heroMovieService;

    public HeroService(HeroDao dao, HeroMovieService heroMovieService) {
        this.dao = dao;
        this.heroMovieService = heroMovieService;
    }

    public List<HeroDto> getHeroes() {
        return dao.findAll().stream()
                .map(hero ->
                        new HeroDto.Builder()
                                .withName(hero.getName())
                                .withMovies(heroMovieService.getPlayedIn(hero.getName()))
                                .build()
                )
                .toList();
    }

    @Override
    public String toString() {
        return "HeroService{" +
                "dao=" + dao +
                ", heroMovieService=" + heroMovieService +
                '}';
    }
}
