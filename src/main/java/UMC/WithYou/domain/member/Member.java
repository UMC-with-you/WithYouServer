package UMC.WithYou.domain.member;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.Post.ScrapedPost;
import UMC.WithYou.domain.travel.Traveler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import UMC.WithYou.domain.rewind.Rewind;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.util.ArrayList;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "email", columnList = "email", unique = true))
public class Member extends BaseEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Embedded
    private Email email;
    @Embedded
    private Identifier identifier;
    @Embedded
    private Name name;

    @Getter
    @Enumerated(EnumType.STRING)
    private MemberType memberType;



    @Getter
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ScrapedPost> scrapedPosts;

    @Getter
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Traveler> travelers = new ArrayList<>();

    @Getter
    private String imageUrl;

    @Getter
    @OneToMany(mappedBy="writer", cascade = CascadeType.ALL)
    private List<Rewind> rewindList = new ArrayList<>();


    @Builder
    public Member(String email,String identifier,String name,MemberType memberType) {
        this.email=new Email(email);
        this.identifier=new Identifier(identifier);
        this.name = new Name(name);
        this.memberType=memberType;
    }

    public String getEmail() {
        return this.email.getValue();
    }
    public String getIdentifier() {
        return this.identifier.getValue();
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

    public void addTraveler(Traveler traveler) {
        this.travelers.add(traveler);
    }
}
