package talmo5.talmorello.board.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.boarduser.entity.BoardUser;

@Getter
@Setter
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

    @Column
    private BoardColor boardColor;

    @OneToMany
    private List<BoardUser> BoardUser = new ArrayList<>();

    @OneToMany
    private List<talmo5.talmorello.column.entity.Column> columns = new ArrayList<>();

    public Board(BoardColor boardColor, String content, String title) {
        this.title = title;
        this.content = content;
        this.boardColor = boardColor;
    }

    public void addUser(/*User user*/){
        //BoardUser.add(new BoardUser(new BoardUserPK(this, user)));
    }

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

}
