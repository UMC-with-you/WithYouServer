package UMC.WithYou.domain.notice;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private int state; //0: 여행전, 1: 여행중, 2: 여행후

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;    //노티스를 만든사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="log_id")
    private Travel travel;


}
