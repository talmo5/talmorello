package talmo5.talmorello.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.todo.constant.TodoStatus;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private TodoStatus todoStatus;

    @JoinColumn(name = "card_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Card card;

    public void updateTodo(String todoContent) {
        this.content = todoContent;
    }

    public void check() {
        this.todoStatus = TodoStatus.CHECK;
    }
}
