package hm22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroMovieService {

    Map<String, List<String>> heroMovies;

    public HeroMovieService() {
        this.heroMovies = new HashMap<>();
    }

    public List<String> getPlayedIn(String heroName) {
        List<String> movies = heroMovies.get(heroName);
        if (movies == null) {
            return new ArrayList<>();
        }
        return movies;
    }
}
