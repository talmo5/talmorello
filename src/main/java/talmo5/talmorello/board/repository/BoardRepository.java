package talmo5.talmorello.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
