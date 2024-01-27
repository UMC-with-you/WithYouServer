package UMC.WithYou.repository.dummy;


import UMC.WithYou.domain.dummy.DummyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyMemberRepository extends JpaRepository<DummyMember, Long> {
}
