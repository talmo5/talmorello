package talmo5.talmorello.card.repository.custom;

import talmo5.talmorello.column.entity.Column;

public interface CustomCardRepository {

    public void addOneToCardOrders(int order, Column column);
}
