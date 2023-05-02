package hm22;

import java.util.List;


public class HeroDto {

    private String name;
    private List<String> movies;


    public String getName() {
        return name;
    }

    public List<String> getMovies() {
        return movies;
    }


    public static class Builder {
        private final HeroDto heroDto;

        public Builder() {
            heroDto = new HeroDto();
        }

        public Builder withName(String name) {
            heroDto.name = name;
            return this;
        }

        public Builder withMovies(List<String> movies) {
            heroDto.movies = movies;
            return this;
        }

        public HeroDto build() {
            return heroDto;
        }

    }
}
