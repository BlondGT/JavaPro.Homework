package hm21;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HeroDaoImpl implements HeroDao{

    private final DataSource dataSource;

    @Override
    public List<Hero> findAll() {
        var sql = "select * from hero_information";
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
                    .name(result.getString("Name"))
                    .gender(result.getString("gender"))
                    .eyeColor(result.getString("Eye color"))
                    .race(result.getString("race"))
                    .hairColor(result.getString("Hair color"))
                    .height(result.getInt("height"))
                    .publisher_id(result.getLong("publisher id"))
                    .skinColor(result.getString("Skin color"))
                    .alignment(result.getString("alignment"))
                    .weight(result.getInt("weight"))
                    .build());
        }
        return heroes;
    }

    @Override
    public List<Hero> findByName(String name) {

        var sql = "select * from hero_information where Name = '" + name + "'";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var result = statement.executeQuery(sql);
            return mapHeroes(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Hero hero) {

    var sql = "insert into hero_information (Name, gender, Eye color, race, Hair color, height, publisher_id, Skin color, alignment, weight) " +
            "values ('" + hero.getName() + "', '" + hero.getGender() + "', '" + hero.getEyeColor() + "', '" + hero.getRace() + "', '" +
            hero.getHairColor() + "', " + hero.getHeight() + ", " + hero.getPublisher_id() + ", '" + hero.getSkinColor() + "', '" +
            hero.getAlignment() + "', " + hero.getWeight() + ")";
        try (var connection = dataSource.getConnection();
            var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Hero hero) {

        var sql = "update hero_information set " +
                "Name ='"  + hero.getName() + "', " +
                "gender ='" + hero.getGender() + "', " +
                "Eye color ='" + hero.getEyeColor() + "', " +
                "race ='" + hero.getRace() + "', " +
                "Hair color ='" + hero.getHairColor() + "', " +
                "height =" + hero.getHeight() + ", " +
                "publisher_id =" + hero.getPublisher_id() + ", " +
                "Skin color ='" + hero.getSkinColor() + "', " +
                "alignment ='" + hero.getAlignment() + "', " +
                "weight =" + hero.getWeight() + " " +
                "where id =" + hero.getId();
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {

        var sql = "delete from hero_information where id = '" + id + "'";
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
