package talmo5.talmorello.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
