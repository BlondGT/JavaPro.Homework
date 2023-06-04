package hm22;

import hm21.Hero;
import hm21.HeroDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DummyHeroDao implements HeroDao {

    private final List<Hero> db;

    @Override
    public List<Hero> findAll() {
        return db;
    }

    @Override
    public List<Hero> findByName(String name) {
        return db.stream()
                .filter(hero -> hero.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Hero findById(Long id) {
        return db.get(id.intValue());
    }

    @Override
    public Hero create(Hero hero) {
        db.add(hero);
        return hero;
    }

    @Override
    public Hero update(Hero hero) {
        if (delete(hero.getId()))
            db.add(hero);
        return hero;
    }

    @Override
    public boolean delete(Long id) {
        var heroDel = db.stream()
                .filter(hero -> hero.getId().equals(id))
                .findAny()
                .orElseThrow();

        return db.remove(heroDel);
    }
}

