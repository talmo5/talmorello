package talmo5.talmorello.board.entity;

import jakarta.persistence.*;
import lombok.*;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.board.constant.BoardColor;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardColor boardColor;

    public void update(String title, String content, BoardColor boardColor) {
        if (!title.isBlank()) this.title = title;
        if (!content.isBlank()) this.content = content;
        this.boardColor = boardColor;
    }
}
