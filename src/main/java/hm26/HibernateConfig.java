package hm26;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hm21.HeroDao;
import hm21.HeroDaoImpl;
import hm22.HeroFabric;
import hm22.HeroMovieService;
import hm22.HeroService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("hm26")
public class HibernateConfig {

    @Bean
    private static DataSource hikariDataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("hm26");
        return sessionFactory;
    }

    @Bean
    public HeroService heroService() {
        return HeroFabric.createService(hikariDataSource());
    }

    @Bean
    public HeroDao heroDao() {
        return new HeroDaoImpl(hikariDataSource());
    }

    @Bean
    public HeroMovieService heroMovieService() {
        return new HeroMovieService();
    }
}
