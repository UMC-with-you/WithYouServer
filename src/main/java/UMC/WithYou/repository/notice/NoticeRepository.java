package UMC.WithYou.repository.notice;

import UMC.WithYou.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
    //Page<Notice> findAllByTravelLog(TravelLog log, PageRequest pageRequest);
}
