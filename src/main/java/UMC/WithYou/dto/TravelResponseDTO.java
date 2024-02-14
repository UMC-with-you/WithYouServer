package UMC.WithYou.dto;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.domain.travel.TravelStatus;
import UMC.WithYou.domain.travel.Traveler;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class TravelResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class ConfigurationResponseDTO {
        private Long travelId;
    }

    @Getter
    public static class ThumbnailResponseDTO{
        private Long travelId;
        private String title;
        private LocalDate startDate;
        private LocalDate endDate;
        private TravelStatus status;
        private String imageUrl;
        public ThumbnailResponseDTO(Travel travel){
            travelId = travel.getId();
            title = travel.getTitle();
            startDate = travel.getStartDate();
            endDate = travel.getEndDate();
            status = travel.getStatus();
            imageUrl = travel.getImageUrl();
        }
    }

    @Getter
    public static class TravelerResponseDTO{
        private Long memberId;
        private String name;
        private String imageUrl;
        public TravelerResponseDTO(Member member){
            this.memberId = member.getId();
            this.name = member.getName();;
            this.imageUrl = member.getImageUrl();
        }
    }

    @Getter
    public static class JoinResponseDTO{
        private Long memberId;
        private Long travelId;

        public JoinResponseDTO(Traveler traveler){
            this.memberId = traveler.getMember().getId();
            this.travelId = traveler.getTravel().getId();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class InvitationCodeResponseDTO{
        private Long travelId;
        private String invitationCode;
    }

    @Getter
    @AllArgsConstructor
    public static class DeletionResponseDTO{
        private Long travelId;
    }

    @Getter
    @AllArgsConstructor
    public static class LeaveResponseDTO{
        private Long travelId;
        private Long memberId;

    }

}
