package UMC.WithYou.domain.cloud;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cloud extends BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="travel_id")
    private Travel travel;

    private List<CloudMedia> pictureDate;
}
