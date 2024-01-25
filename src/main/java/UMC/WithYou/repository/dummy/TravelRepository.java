package UMC.WithYou.repository.dummy;

import UMC.WithYou.domain.dummy.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
