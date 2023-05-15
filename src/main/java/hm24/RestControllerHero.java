package hm24;

import hm21.Hero;
import hm21.HeroDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestControllerHero {

    private final HeroDao dao;

    @GetMapping("/heroes")
    public List<Hero> getAllHeroes() {
        return dao.findAll();
    }

    @GetMapping("/heroes/{id}")
    public Hero getHeroById(@PathVariable("id") Long heroId) {
        return dao.findById(heroId);
    }

    @PostMapping("/heroes")
    public void createHero(@RequestBody Hero hero) {
        dao.create(hero);
    }

    @PutMapping("/heroes/{id}")
    public void updateHeroById(@PathVariable("id") Long heroId, @RequestBody Hero hero) {
        dao.findById(heroId);
        dao.update(hero);
    }

    @DeleteMapping("/heroes/{id}")
    public void deleteHeroById(@PathVariable("id") Long heroId) {
        dao.delete(heroId);
    }
}
