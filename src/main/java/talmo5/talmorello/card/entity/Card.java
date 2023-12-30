package talmo5.talmorello.card.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.card.constant.Priority;
import talmo5.talmorello.user.entity.User;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

    @Column(nullable = false)
    private Integer orders;

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @JoinColumn(name = "column_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private talmo5.talmorello.column.entity.Column column;

    public void changeCardTitle(String cardTitle) {
        this.title = cardTitle;
    }

    public void changeCardDescription(String cardDescription) {
        this.content = cardDescription;
    }

    public void changeCardDate(LocalDateTime startDate, LocalDateTime dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
}
