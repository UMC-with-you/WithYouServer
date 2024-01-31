package UMC.WithYou.repository.cloud;

import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.NoticeCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CloudRepository extends JpaRepository<Cloud,Long> {
    Optional<Cloud> findByMember(Member member);
}
