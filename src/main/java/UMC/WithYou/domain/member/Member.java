package UMC.WithYou.domain.member;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.Post.ScrapedPost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "email", columnList = "email", unique = true))
public class Member extends BaseEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Embedded
    private Email email;
    @Embedded
    private Name name;

    @Getter
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Getter
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ScrapedPost> scrapedPosts;

    @Builder
    public Member(String email, String name,String phoneNumber,MemberType memberType) {
        this.email=new Email(email);
        this.name = new Name(name);
        this.memberType=memberType;
    }

    public String getEmail() {
        return this.email.getValue();
    }

    public String getName() {
        return this.name.getValue();
    }

    public void changeName(String name){
        this.name = new Name(name);
    }

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return  Objects.equals(name, member.name)
                && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name );
    }

    public boolean isSameId(Long memberId) {
        return this.id.equals(memberId);
    }
}
