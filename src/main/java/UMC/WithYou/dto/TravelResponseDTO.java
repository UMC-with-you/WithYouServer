package UMC.WithYou.dto;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
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
        private String imageUrl;
        public ThumbnailResponseDTO(Travel travel){
            travelId = travel.getId();
            title = travel.getTitle();
            startDate = travel.getStartDate();
            endDate = travel.getEndDate();
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
}
