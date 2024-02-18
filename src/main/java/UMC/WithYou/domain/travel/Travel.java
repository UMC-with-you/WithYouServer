package UMC.WithYou.domain.travel;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.member.Member;
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
    private String imageFileName;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

    private String invitationCode;



    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Traveler> travelers = new ArrayList<>();

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Rewind> rewinds;

    public Travel(Member member, String title, LocalDate startDate, LocalDate endDate, String url, String imageFileName) {
        this.member = member;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = url;
        this.imageFileName = imageFileName;
    }

    public void edit(String title, LocalDate startDate, LocalDate endDate, String url, String imageFileName){
        this.title = title;
        this.startDate =startDate;
        this.endDate = endDate;
        this.imageUrl = url;
        this.imageFileName = imageFileName;
    }


    public void addTravelMember(Traveler traveler){
        this.travelers.add(traveler);
    }
    public List<Member> getTravelMembers(){
        List<Member> travelMembers = new ArrayList<>();
        for (Traveler traveler : getTravelers()){
            travelMembers.add(traveler.getMember());
        }
        return travelMembers;
    }


    public boolean isTraveler(Member member){
        for (Traveler traveler: getTravelers()){
            if (traveler.isMember(member)) {
                return true;
            }
        }
        return false;

    }

    public boolean validateOwnership(Member member) {
        return this.member.isSameId(member.getId());
    }

    public boolean hasInvitationCode() {
        return this.invitationCode != null;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public void setTravelStatus(LocalDate localDate){
        if(endDate.isBefore(localDate)){
            this.status = TravelStatus.BYGONE;
        }
        else if (!startDate.isAfter(localDate)){
            this.status = TravelStatus.ONGOING;
        }
        else{
            this.status = TravelStatus.UPCOMING;
        }
    }

    public void leave(Member travelMember) {

        for (Traveler traveler : getTravelers()){
            if (traveler.isMember(travelMember)){
                travelers.remove(traveler);
                break;
            }
        }

    }
}
