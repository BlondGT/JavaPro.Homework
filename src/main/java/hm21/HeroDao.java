package hm21;

import java.util.List;

public interface HeroDao {

    List<Hero> findAll();
    List<Hero> findByName(String name);
    Hero findById(Long id);
    Hero create(Hero hero);
    Hero update(Hero hero);
    boolean delete(Long id);

}
