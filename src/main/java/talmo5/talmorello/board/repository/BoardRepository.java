package talmo5.talmorello.board.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import talmo5.talmorello.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b LEFT JOIN b.catalogList c ON b.id = c.board.id LEFT JOIN c.cardList cl ON c.id = cl.catalog.id WHERE b.id = :boardId")
    Optional<Board> findByIdWithCatalogListAndCardList(Long boardId);
}
