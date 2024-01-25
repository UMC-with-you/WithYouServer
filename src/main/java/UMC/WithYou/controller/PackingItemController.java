package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.domain.PackingItem;
import UMC.WithYou.dto.PackingItemRequest.*;
import UMC.WithYou.dto.PackingItemResponse.*;
import UMC.WithYou.service.PackingItemService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackingItemController {
    private final PackingItemService packingItemService;

    public PackingItemController(PackingItemService packingItemService){
        this.packingItemService = packingItemService;
    }

    @PostMapping("api/v1/packing_items")
    public ApiResponse<AdditionResponseDTO> addPackingItem(
            @RequestBody @Valid AdditionRequestDTO additionRequestDTO){
        Long travelId = additionRequestDTO.getTravelId();
        String itemName = additionRequestDTO.getItemName();
        PackingItem packingItem = packingItemService.addPackingItem(travelId, itemName);


        return ApiResponse.onSuccess(AdditionResponseDTO
                .builder()
                .packingItemId(packingItem.getId())
                .createdAt(packingItem.getCreatedAt())
                .build());
    }


    @GetMapping("api/v1/packing_items")
    public ApiResponse<List<SearchResponseDTO>> getPackingItems(@RequestParam("travel_id") Long travelId){
        List<PackingItem> packingItems = packingItemService.findPackingItems(travelId);

        return ApiResponse.onSuccess(packingItems.stream()
                .map(p -> new SearchResponseDTO(p))
                .toList());

    }

    @DeleteMapping("api/v1/packing_items/{packingItemId}")
    public ApiResponse<DeletionResponseDTO> deletePackingItem(@PathVariable Long packingItemId){
        packingItemService.deletePackingItem(packingItemId);

        return ApiResponse.onSuccess(new DeletionResponseDTO(packingItemId));
    }


    @PatchMapping("api/v1/packing_items/{packingItemId}/packer_choice")
    public ApiResponse<PackerChoiceResponseDTO> choosePacker(
        @PathVariable Long packingItemId, @RequestParam("packer_id") Long packerId) {
        Boolean checkboxState = packingItemService.setPacker(packingItemId, packerId);
        return ApiResponse.onSuccess(new PackerChoiceResponseDTO(packingItemId, packerId, checkboxState));
    }

    @PatchMapping("api/v1/packing_items/{packingItemId}")
    public ApiResponse<ToggleResponseDTO> toggleCheckbox(@PathVariable Long packingItemId){
        Boolean checkboxState = packingItemService.toggleCheckbox(packingItemId);
        return ApiResponse.onSuccess(new ToggleResponseDTO(packingItemId, checkboxState));
    }

}

