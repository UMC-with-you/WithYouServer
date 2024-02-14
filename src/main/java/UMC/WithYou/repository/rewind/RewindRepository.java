package UMC.WithYou.repository.rewind;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.domain.travel.Travel;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RewindRepository extends JpaRepository<Rewind, Long> {
    Optional<Rewind> findByTravelAndWriterAndDay(Travel travel, Member member, Integer day);
    List<Rewind> findAllByTravel(Travel travel);
    List<Rewind> findAllByTravelAndDay(Travel travel, Integer day);
}
