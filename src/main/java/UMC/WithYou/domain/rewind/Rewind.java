package UMC.WithYou.domain.rewind;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Rewind extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "travel_id")
//    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column
    private Integer day;

    @Column
    private Long mvpCandidateId;

    @Enumerated(EnumType.STRING)
    @Column
    private Mood mood;

    @Column(length = 100)
    private String comment;

    @OneToMany(mappedBy="rewind", cascade = CascadeType.ALL)
    private List<RewindQna> rewindQnaList = new ArrayList<>();

//    public void setTravel(Travel travel) {
//        if(this.travel != null)
//            travel.getRewindList().remove(this);
//        this.travel = travel;
//        travel.getRewindList().add(this);
//    }

    public void setWriter(Member member) {
        if(this.writer != null)
            member.getRewindList().remove(this);
        this.writer = member;
        member.getRewindList().add(this);
    }

}
