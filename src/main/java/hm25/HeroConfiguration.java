package hm25;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hm21.HeroDao;
import hm21.HeroDaoImpl;
import hm22.HeroFabric;
import hm22.HeroMovieService;
import hm22.HeroService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("java")
@PropertySource("classpath:application.properties")
public class HeroConfiguration {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;


    @Bean
    public HeroService heroService() {
        return HeroFabric.createService(dataSource());
    }

    @Bean
    public HeroDao heroDao() {
        return new HeroDaoImpl(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        var db = new HikariConfig();
        db.setJdbcUrl(dbUrl);
        db.setUsername(dbUsername);
        db.setPassword(dbPassword);
        return new HikariDataSource(db);
    }

    @Bean
    public HeroMovieService heroMovieService() {
        return new HeroMovieService();
    }
}
