package UMC.WithYou.repository.post;

import UMC.WithYou.domain.Post.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.travel.id = :travelId")
    List<Post> findByTravelId(Long travelId);

}
