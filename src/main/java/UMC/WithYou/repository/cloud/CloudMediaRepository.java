package UMC.WithYou.repository.cloud;

import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.cloud.CloudMedia;
import UMC.WithYou.domain.notice.NoticeCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudMediaRepository extends JpaRepository<CloudMedia,Long> {
    List<CloudMedia> findAllByCloud(Cloud cloud);
}
