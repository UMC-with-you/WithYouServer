package UMC.WithYou.repository.post;

import UMC.WithYou.domain.Post.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
