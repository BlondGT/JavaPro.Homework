package hm25;

import hm21.HeroDaoImpl;
import hm22.HeroService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class HeroMain {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(HeroConfiguration.class);
        var heroService = context.getBean(HeroService.class);
        var heroDao = context.getBean(HeroDaoImpl.class);
        Long heroId = new Scanner(System.in).nextLong();

        System.out.println(heroDao.findById(heroId));
    }
}
