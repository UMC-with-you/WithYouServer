package UMC.WithYou.repository.dummy;


import UMC.WithYou.domain.dummy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
