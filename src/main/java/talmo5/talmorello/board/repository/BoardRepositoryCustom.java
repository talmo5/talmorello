package talmo5.talmorello.board.repository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import talmo5.talmorello.board.dto.GetBoardDTO;

public interface BoardRepositoryCustom {
    List<GetBoardDTO.ColumnResponse> findByIdWithColumnListAndCardList(@Param("boardId") Long boardId);
}

