package hm25;

import hm22.HeroService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HeroMain {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(HeroConfiguration.class);
        var heroService = context.getBean(HeroService.class);

        System.out.println(heroService);
    }
}
