package UMC.WithYou.dto;

import UMC.WithYou.domain.PackingItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PackingItemRequest {
    @Getter
    public static class AdditionRequestDTO{
        private Long travelId;
        private String itemName;
    }



}
