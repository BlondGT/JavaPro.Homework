package bank.hm28.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends JpaRepository<SpringDataUser, Long> {

    SpringDataUser findByUid(String uid);
}
