package talmo5.talmorello.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByKakaoId(Long id);

}
