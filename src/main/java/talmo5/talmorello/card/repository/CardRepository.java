package talmo5.talmorello.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.repository.custom.CustomCardRepository;

public interface CardRepository extends JpaRepository<Card, Long>, CustomCardRepository {

}
