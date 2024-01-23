package UMC.WithYou.repository.post;

import UMC.WithYou.domain.Post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
