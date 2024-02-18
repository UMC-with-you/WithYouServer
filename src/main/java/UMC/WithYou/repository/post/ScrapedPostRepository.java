package UMC.WithYou.repository.post;

import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.domain.Post.ScrapedPost;
import UMC.WithYou.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapedPostRepository extends JpaRepository<ScrapedPost, Long> {

    Optional<ScrapedPost> findScrapedPostByMemberAndPost(Member member, Post post);
}
