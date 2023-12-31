package talmo5.talmorello.board.repository.custom;

import org.springframework.data.repository.query.Param;
import talmo5.talmorello.board.dto.GetBoardDTO;

import java.util.List;

public interface BoardRepositoryCustom {
    List<GetBoardDTO.ColumnResponse> findByIdWithColumnListAndCardList(@Param("boardId") Long boardId);
}
