package UMC.WithYou.domain.dummy;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DummyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;;

    public Long getId(){
        return this.id;
    }
}
