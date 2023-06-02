package hm28;

import hm28.User.SpringDataUser;
import hm28.User.SpringDataUserService;
import hm28.User.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EntityScan("hm28.User")
public class SpringDataUserApplication implements CommandLineRunner {

    private final SpringDataUserService userService;
    UUID uid = UUID.randomUUID();

    public static void main(String[] args) {
        SpringApplication.run(SpringDataUserApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Users: " + userService.getAllUsers());
        System.out.println("User: " + userService.getUserById(3L));
        System.out.println("New user: " + userService.createUser(SpringDataUser.builder()
                .name("Test3")
                .email("test3@gmail.com")
                .uid(uid.toString())
                .role(UserRole.CUSTOMER)
                .build()));
        System.out.println(userService.updateUser(uid.toString(), "TestUpdate"));
        System.out.println(userService.deleteUser("14c4d17d-e90c-4342-ab8a-4d2abe476ebe"));
    }
}
