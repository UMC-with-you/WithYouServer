package UMC.WithYou.service;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.domain.PackingItem;
import UMC.WithYou.domain.dummy.DummyTravel;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.repository.PackingItemRepository;
import UMC.WithYou.repository.dummy.DummyTravelRepository;
import UMC.WithYou.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PackingItemService {
    private final PackingItemRepository packingItemRepository;
    private final DummyTravelRepository travelRepository;
    private final MemberRepository memberRepository;


    public PackingItemService(PackingItemRepository packingItemRepository, DummyTravelRepository travelRepository, MemberRepository memberRepository){
        this.packingItemRepository = packingItemRepository;
        this.travelRepository = travelRepository;
        this.memberRepository = memberRepository;
    }

    public PackingItem addPackingItem(Long travelId, String itemName) {
        DummyTravel travel = travelRepository.findById(travelId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND)
        );

        PackingItem packingItem =  PackingItem.createPackingItem(travel, itemName);

        return packingItemRepository.save(packingItem);
    }

    public List<PackingItem> findPackingItems(Long travelId){
        DummyTravel travel = travelRepository.findById(travelId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND)
        );
        return packingItemRepository.findByTravelId(travelId);
    }

    public void deletePackingItem(Long packingItemId){
        PackingItem packingItem = this.findPeckingItemById(packingItemId);
        packingItemRepository.delete(packingItem);
    }

    public Boolean setPacker(Long packingItemId, Long packerId){
        PackingItem packingItem = this.findPeckingItemById(packingItemId);

        Member packer = memberRepository.findById(packerId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        if (packingItem.getPacker() != null && packingItem.getPacker().isSameId(packerId)){

            if (packingItem.getIsChecked()) {
                packingItem.toggleCheckbox();
            }
            packer = null;
        }
        packingItem.setPacker(packer);
        return packingItem.getIsChecked();
    }

    public Boolean toggleCheckbox(Long packingItemId) {
        PackingItem packingItem = this.findPeckingItemById(packingItemId);

        if (packingItem.isPackerChosen()){
            return packingItem.getIsChecked();
        }
        return packingItem.toggleCheckbox();
    }



    private PackingItem findPeckingItemById(Long packingItemId){
        return packingItemRepository.findById(packingItemId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.PACKING_ITEM_NOT_FOUND)
        );
    }
}
