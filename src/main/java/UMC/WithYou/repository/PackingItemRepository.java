package UMC.WithYou.repository;

import UMC.WithYou.domain.PackingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingItemRepository extends JpaRepository<PackingItem, Long> {
}
