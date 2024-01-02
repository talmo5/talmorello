package talmo5.talmorello.todo.repository.custom;

import static talmo5.talmorello.board.entity.QBoard.board;
import static talmo5.talmorello.card.entity.QCard.card;
import static talmo5.talmorello.column.entity.QColumn.column;
import static talmo5.talmorello.todo.entity.QTodo.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.board.entity.Board;

@RequiredArgsConstructor
public class CustomTodoRepositoryImpl implements CustomTodoRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Board> findBoardByTodoId(Long todoId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(board)
                .from(todo)
                .join(card).on(todo.card.id.eq(card.id))
                .join(column).on(card.column.id.eq(column.id))
                .join(board).on(column.board.id.eq(board.id))
                .where(todo.id.eq(todoId))
                .fetchOne()
        );
    }
}
