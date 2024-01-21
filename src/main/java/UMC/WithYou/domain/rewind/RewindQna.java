package UMC.WithYou.domain.rewind;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RewindQna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rewind_id")
    private Rewind rewind;

    @Column(length = 255)
    private String answer;

    public void setRewind(Rewind rewind) {
        if(this.rewind != null)
            rewind.getRewindQnaList().remove(this);
        this.rewind = rewind;
        rewind.getRewindQnaList().add(this);
    }

}
