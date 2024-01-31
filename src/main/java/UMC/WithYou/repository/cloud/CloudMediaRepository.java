package UMC.WithYou.repository.cloud;

import UMC.WithYou.domain.cloud.CloudMedia;
import UMC.WithYou.domain.notice.NoticeCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudMediaRepository extends JpaRepository<CloudMedia,Long> {
}
