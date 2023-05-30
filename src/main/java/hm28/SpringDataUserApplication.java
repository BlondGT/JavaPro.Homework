package hm28;

import hm28.User.SpringDataUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EntityScan("hm28.User")
public class SpringDataUserApplication implements CommandLineRunner {

    private final SpringDataUserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataUserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userService.getAllUsers());
    }
}
