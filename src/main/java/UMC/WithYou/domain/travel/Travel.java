package UMC.WithYou.domain.travel;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.PackingItem;
import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.rewind.Rewind;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Travel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

    private String invitationCode;



    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Traveler> travelers;

//    @OneToMany
//    private List<PackingItem> packingItems;
//
//    @OneToMany
//    private List<Rewind> rewinds;
//
//    @OneToMany
//    private List<Post> posts;
//
//    @OneToMany
//    private List<Notice> notices;

    public Travel(Member member, String title, LocalDate startDate, LocalDate endDate, String url) {
        this.member = member;
        this.title = title;
        this.startDate =startDate;
        this.endDate = endDate;
        this.imageUrl = url;
    }

    public void edit(String title, LocalDate startDate, LocalDate endDate, String url){
        this.title = title;
        this.startDate =startDate;
        this.endDate = endDate;
        this.imageUrl = url;
    }


    public List<Member> getTravelMembers(){
        List<Member> travelMembers = new ArrayList<>();
        travelMembers.add(this.member);
        for (Traveler traveler : getTravelers()){
            travelMembers.add(traveler.getMember());
        }
        return travelMembers;
    }


    public boolean isTraveler(Member member){
        if (validateOwnership(member)){
            return true;
        }

        for (Traveler traveler: getTravelers()){
            if (traveler.isMember(member)) {
                return true;
            }
        }
        return false;

    }

    private boolean validateOwnership(Member member) {
        return this.member.isSameId(member.getId());
    }



}
