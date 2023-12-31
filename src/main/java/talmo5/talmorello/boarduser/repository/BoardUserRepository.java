package talmo5.talmorello.boarduser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.boarduser.entity.BoardUser;
import talmo5.talmorello.boarduser.repository.custom.BoardUserRepositoryCustom;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long>, BoardUserRepositoryCustom {
}
