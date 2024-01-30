package UMC.WithYou.repository.post;

import UMC.WithYou.domain.Post.ScrapedPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapedPostRepository extends JpaRepository<ScrapedPost, Long> {
}
