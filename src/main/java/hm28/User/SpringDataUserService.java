package hm28.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpringDataUserService {

    private final SpringDataUserRepository userRepository;

    public SpringDataUser createUser(SpringDataUser user) {
        return userRepository.save(user);
    }

    public SpringDataUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    public List<SpringDataUser> getAllUsers() {
        return userRepository.findAll();
    }

    public SpringDataUser updateUser(SpringDataUser user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
