package talmo5.talmorello.column.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.board.entity.Board;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "columns")
public class Column extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private Integer orders;

  /*  @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board; */

  public Column(String title, Integer orders) {
    this.title = title;
    this.orders = orders;
  }

  public void updateTitle(String title) {
    this.title = title;
  }

}
