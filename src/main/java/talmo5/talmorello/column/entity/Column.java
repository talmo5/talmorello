package talmo5.talmorello.column.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.card.entity.Card;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "columns")
public class Column extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @jakarta.persistence.Column(length = 50, nullable = false)
  private String title;

  @jakarta.persistence.Column(nullable = false)
  private Integer orders;

  @JoinColumn(name = "board_id")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Board board;

  @OneToMany(mappedBy = "column", cascade = CascadeType.REMOVE)
  private List<Card> cardList;

  public Column(String title, Integer orders) {
    this.title = title;
    this.orders = orders;
  }

  public void modifyColumnName(String title) {
    this.title = title;
  }

  public void changeOrders(Integer orders) {this.orders = orders;}


}
