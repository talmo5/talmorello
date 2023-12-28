package talmo5.talmorello.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import talmo5.talmorello.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c.orders FROM Card c ORDER BY c.orders DESC LIMIT 1")
    Integer getLastOrders();
}
