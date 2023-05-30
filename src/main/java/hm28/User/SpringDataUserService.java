package hm28.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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

    @Transactional
    public SpringDataUser updateUser(String uid, String newName) {
       var user = userRepository.findByUid(uid);
       user.setName(newName);
       return user;
    }

    public boolean deleteUser(String uid) {
        var user = userRepository.findByUid(uid);
        userRepository.delete(user);
        return true;
    }
}
