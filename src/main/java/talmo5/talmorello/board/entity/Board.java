package talmo5.talmorello.board.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.boarduser.entity.BoardUser;

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

    @Singular("BoardUsers")
    @OneToMany
    private List<BoardUser> boardUser = new ArrayList<>();

    @Singular("Columns")
    @OneToMany
    private List<talmo5.talmorello.column.entity.Column> columns = new ArrayList<>();
}
