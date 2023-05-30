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
        System.out.println(userService.getAllUsers());
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.createUser(SpringDataUser.builder()
                .name("Test")
                .email("test@gmail.com")
                .uid(uid.toString())
                .role(UserRole.CUSTOMER)
                .build()));
        System.out.println(userService.updateUser(SpringDataUser.builder().build()));
        System.out.println(userService.deleteUser(16L));
    }
}
