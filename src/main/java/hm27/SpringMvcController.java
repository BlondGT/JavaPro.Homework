package hm27;

import hm21.Hero;
import hm21.HeroDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SpringMvcController {

    private final HeroDao heroDao;

    @GetMapping
    public String heroes(Model model) {
        model.addAttribute("name", "Visitors");
        model.addAttribute("heroes", heroDao.findAll());
        return "heroes/index";
    }

    @GetMapping("/{id}")
    public String hero(Model model, @PathVariable("id") Long id) {
        Hero hero = heroDao.findById(id);
        if (hero == null) {
            throw new HeroNotFoundException("Hero not found with ID: " + id);
        }
        model.addAttribute("hero", hero);
        return "heroes/hero";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("hero", Hero.builder().build());
        return "heroes/create";
    }

    @PostMapping("/add")
    public String create(Hero hero) {
        heroDao.create(hero);
        return "redirect:/";
    }
}
