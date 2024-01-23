package UMC.WithYou.repository.post;

import UMC.WithYou.domain.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}
