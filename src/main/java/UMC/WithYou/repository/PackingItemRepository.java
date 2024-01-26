package UMC.WithYou.repository;

import UMC.WithYou.domain.PackingItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingItemRepository extends JpaRepository<PackingItem, Long> {
    @Query("select p from PackingItem p where p.travel.id = :travelId")
    List<PackingItem> findByTravelId(Long travelId);
}
