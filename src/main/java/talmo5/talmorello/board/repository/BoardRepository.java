package talmo5.talmorello.board.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.MultiValueMap;
import talmo5.talmorello.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select b.id, b.title, b.content, b.board_color, c.id, c.title, c.orders, cd.id, cd.title, cd.content, cd.orders, u.id, u.username"
            + " from board b"
            + "    left join talmorello.columns c on b.id = c.board_id"
            + "    left join talmorello.card cd on c.id = cd.column_id"
            + "    join talmorello.user u on cd.user_id = u.id"
            + "    where b.id = :boardId"
            , nativeQuery = true)
    List<Map<String,?>> findByIdWithCatalogListAndCardList(@Param("boardId") Long boardId);
}
