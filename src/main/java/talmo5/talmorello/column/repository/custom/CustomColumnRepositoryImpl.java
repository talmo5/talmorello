package talmo5.talmorello.column.repository.custom;

import static talmo5.talmorello.column.entity.QColumn.column;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.board.entity.Board;

@RequiredArgsConstructor
public class CustomColumnRepositoryImpl implements CustomColumnRepository {

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  public void addOneToColumnOrders(Board board, int newOrders, int oldOrders) {
    /*
    UPDATE Column SET orders = orders + 1
    WHERE orders <= newOrders AND orders > oldOrders
     */
    jpaQueryFactory.
            update(column)
            .set(column.orders, column.orders.add(1))
            .where(column.board.eq(board),
                    column.orders.loe(newOrders),
                    column.orders.gt(oldOrders)
            )
            .execute();
  }

  @Override
  public void subtractOneToColumnOrders(Board board, int newOrders, int oldOrders) {
      /*
      UPDATE Column SET orders = orders - 1
      WHERE orders >= newOrders AND orders < oldOrders
       */
    jpaQueryFactory.
            update(column)
            .set(column.orders, column.orders.add(1))
            .where(column.board.eq(board),
                    column.orders.goe(newOrders),
                    column.orders.lt(oldOrders)
            )
            .execute();
  }

}
