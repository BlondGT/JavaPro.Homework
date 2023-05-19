package hm26;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class HibernateMain {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        var sessionFactory = context.getBean(SessionFactory.class);

        try (var session = sessionFactory.openSession()) {
            var hero = session.find(HibernateHeroes.class, 1L);
            System.out.println("Found hero: " + hero);
        }
    }
}
