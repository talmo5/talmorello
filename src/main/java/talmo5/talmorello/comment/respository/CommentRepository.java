package talmo5.talmorello.comment.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
