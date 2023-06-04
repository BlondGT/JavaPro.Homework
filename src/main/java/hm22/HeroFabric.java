package hm22;

import hm21.Hero;
import hm21.HeroDao;
import hm21.HeroDaoImpl;

import javax.sql.DataSource;
import java.util.List;

public class HeroFabric {

    public static HeroService createService(DataSource dataSource) {
        return new HeroService(createDao(dataSource), heroMovieService());
    }

    public static HeroDao createDao(DataSource dataSource) {
        return new HeroDaoImpl(dataSource);
    }

    public static HeroService createDummyService(List<Hero> heroes) {
        return new HeroService(new DummyHeroDao(heroes), heroMovieService());
    }

    private static HeroMovieService heroMovieService() {
        return new HeroMovieService();
    }

}
