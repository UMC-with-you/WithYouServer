package UMC.WithYou.repository;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Optional<Travel> findByInvitationCode(String invitationCode);

}
