package UMC.WithYou.domain.cloud;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class CloudMedia {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private List<String> url;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cloud_id")
    private Cloud cloud;


}
