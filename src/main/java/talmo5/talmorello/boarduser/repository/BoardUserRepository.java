package talmo5.talmorello.boarduser.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.boarduser.repository.custom.BoardUserRepositoryCustom;

@Repository
@RequiredArgsConstructor
public interface BoardUserRepository extends JpaRepository, BoardUserRepositoryCustom {
}
