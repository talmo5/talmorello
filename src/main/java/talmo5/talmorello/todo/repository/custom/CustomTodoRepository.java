package talmo5.talmorello.todo.repository.custom;

import java.util.Optional;
import talmo5.talmorello.board.entity.Board;

public interface CustomTodoRepository {

    Optional<Board> findBoardByTodoId(Long todoId);

}
