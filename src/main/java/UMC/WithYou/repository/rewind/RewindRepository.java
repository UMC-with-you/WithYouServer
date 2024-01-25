package UMC.WithYou.repository.rewind;

import UMC.WithYou.domain.rewind.Rewind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewindRepository extends JpaRepository<Rewind, Long> {
//    Rewind rewindRepository.findByWriterAndDay(Member writer, Integer day);
}
