package talmo5.talmorello.comment.respository.custom;

import java.util.Optional;
import talmo5.talmorello.comment.entity.Comment;

public interface CustomCommentRepository {

    Optional<Comment> findByIdWithUser(Long commentId);

}
