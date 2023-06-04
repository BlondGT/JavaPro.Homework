package hm21;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class HeroDaoImpl implements HeroDao {

    private final DataSource dataSource;

    @Override
    public List<Hero> findAll() {
        var sql = "select * from heroes";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var result = statement.executeQuery(sql);
            return mapHeroes(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Hero> mapHeroes(ResultSet result) throws SQLException {
        var heroes = new ArrayList<Hero>();
        while (result.next()) {
            heroes.add(Hero.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .gender(result.getString("gender"))
                    .eyeColor(result.getString("eye_color"))
                    .race(result.getString("race"))
                    .hairColor(result.getString("hair_color"))
                    .height(result.getInt("height"))
                    .skinColor(result.getString("skin_color"))
                    .alignment(result.getString("alignment"))
                    .weight(result.getInt("weight"))
                    .build());
        }
        return heroes;
    }

    @Override
    public List<Hero> findByName(String name) {

        var sql = "select * from heroes where name = '" + name + "'";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var result = statement.executeQuery(sql);
            return mapHeroes(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hero create(Hero hero) {

        var sql = "insert into heroes (name, gender, eye_color, race, hair_color, height, skin_color, alignment, weight) " +
                "values ('" + hero.getName() + "', '" + hero.getGender() + "', '" + hero.getEyeColor() + "', '" + hero.getRace() + "', '" +
                hero.getHairColor() + "', " + hero.getHeight() + ", '" + hero.getSkinColor() + "', '" +
                hero.getAlignment() + "', " + hero.getWeight() + ")";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hero;
    }

    @Override
    public Hero update(Hero hero) {

        var sql = "update heroes set " +
                "name ='" + hero.getName() + "', " +
                "gender ='" + hero.getGender() + "', " +
                "eye_color ='" + hero.getEyeColor() + "', " +
                "race ='" + hero.getRace() + "', " +
                "hair_color ='" + hero.getHairColor() + "', " +
                "height =" + hero.getHeight() + ", " +
                "skin_color ='" + hero.getSkinColor() + "', " +
                "alignment ='" + hero.getAlignment() + "', " +
                "weight =" + hero.getWeight() + " " +
                "where id =" + hero.getId();
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hero;
    }

    @Override
    public boolean delete(Long id) {

        var sql = "delete from heroes where id = '" + id + "'";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hero findById(Long id) {

        var sql = "select * from heroes where id = " + id + "";

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var result = statement.executeQuery(sql);
            return mapHeroes(result).stream()
                    .findFirst()
                    .orElse(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
