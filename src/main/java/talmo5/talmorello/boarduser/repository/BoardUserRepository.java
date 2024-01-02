package talmo5.talmorello.boarduser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.boarduser.entity.BoardUser;
import talmo5.talmorello.boarduser.repository.custom.BoardUserRepositoryCustom;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long>, BoardUserRepositoryCustom {
}
