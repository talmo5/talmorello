package talmo5.talmorello.column.repository.custom;

import static talmo5.talmorello.board.entity.QBoard.board;
import static talmo5.talmorello.column.entity.QColumn.column;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.column.entity.Column;

@RequiredArgsConstructor
public class CustomColumnRepositoryImpl implements CustomColumnRepository {

  private final JPAQueryFactory jpaQueryFactory;

  private final EntityManager em;


  @Override
  public void subtractOneToColumnOrders(Long boardId, Long columnId, int newOrders, int oldOrders) {
    /*
    UPDATE Column SET orders = orders - 1
    WHERE orders <= newOrders AND orders > oldOrders
     */
    jpaQueryFactory.
            update(column)
            .set(column.orders, column.orders.subtract(1))
            .where(column.board.id.eq(boardId),
                    column.orders.loe(newOrders),
                    column.orders.gt(oldOrders)
            )
            .execute();

    jpaQueryFactory.update(column)
            .set(column.orders, newOrders)
            .where(column.id.eq(columnId))
            .execute();

    em.clear();
  }

  @Override
  public void addOneToColumnOrders(Long boardId, Long columnId, int newOrders, int oldOrders) {
      /*
      UPDATE Column SET orders = orders + 1
      WHERE orders >= newOrders AND orders < oldOrders
       */
    jpaQueryFactory.
            update(column)
            .set(column.orders, column.orders.add(1))
            .where(column.board.id.eq(boardId),
                    column.orders.goe(newOrders),
                    column.orders.lt(oldOrders)
            )
            .execute();

    jpaQueryFactory.update(column)
            .set(column.orders, newOrders)
            .where(column.id.eq(columnId))
            .execute();

    em.clear();
  }

  @Override
  public Optional<Column> getColumnWithBoard(Long columnId) {

    return Optional.ofNullable(jpaQueryFactory.
            selectFrom(column)
            .join(column.board, board).fetchJoin()
            .where(column.id.eq(columnId))
            .fetchOne());
  }

  @Override
  public Long orderCount() {
    return jpaQueryFactory
            .select(column.orders.count())
            .from(column)
            .fetchOne();

  }

}
