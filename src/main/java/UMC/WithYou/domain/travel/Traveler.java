package UMC.WithYou.domain.travel;


import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Traveler extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;

    public Traveler(Travel travel, Member member){
        this.member = member;
        this.travel = travel;
    }
    public boolean isMember(Member member){
        return member.isSameId(member.getId());
    }
}
