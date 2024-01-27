package UMC.WithYou.dto;

import UMC.WithYou.domain.dummy.DummyMember;
import UMC.WithYou.domain.PackingItem;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PackingItemResponse {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class AdditionResponseDTO{
        private Long packingItemId;
        private LocalDateTime createdAt;
    }




    @Getter
    public static class SearchResponseDTO{
        private String itemName;
        private Long packerId;
        private boolean isChecked;
        public SearchResponseDTO(PackingItem packingItem){
            this.itemName = packingItem.getItemName();
            DummyMember packer = packingItem.getPacker();
            if (packer != null){
                this.packerId = packer.getId();
            }
            this.isChecked = packingItem.getIsChecked();
        }
    }


    @Getter
    public static class DeletionResponseDTO{
        private Long packingItemId;
        public DeletionResponseDTO(Long packingItemId){
            this.packingItemId = packingItemId;
        }
    }



    @Getter
    @AllArgsConstructor
    public static class ToggleResponseDTO{
        private Long packingItemId;
        private Boolean checkboxState;
    }

    @Getter
    @AllArgsConstructor
    public static class PackerChoiceResponseDTO{
        private Long packingItemId;
        private Long packerId;
        private Boolean checkboxState;
    }
}
