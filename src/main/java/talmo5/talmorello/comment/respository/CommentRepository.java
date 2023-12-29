package talmo5.talmorello.comment.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.comment.entity.Comment;
import talmo5.talmorello.comment.respository.custom.CustomCommentRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {

}
