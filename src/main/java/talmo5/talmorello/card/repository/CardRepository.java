package talmo5.talmorello.card.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findTopByOrderByOrdersDesc();
}
