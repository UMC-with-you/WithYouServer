package UMC.WithYou.repository.member;

import UMC.WithYou.domain.member.Email;
import UMC.WithYou.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}
