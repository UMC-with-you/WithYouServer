package UMC.WithYou.repository.notice;

import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeCheckRepository extends JpaRepository<NoticeCheck,Long> {
    Optional<NoticeCheck> findByNotice(Notice notice);
    List<NoticeCheck> findAllByIsCheckedIsTrueAndNotice(Long noticeId);
}
