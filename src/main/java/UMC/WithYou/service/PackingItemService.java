package UMC.WithYou.service;

import UMC.WithYou.domain.dummy.DummyMember;
import UMC.WithYou.domain.PackingItem;
import UMC.WithYou.domain.dummy.DummyTravel;
import UMC.WithYou.repository.dummy.DummyMemberRepository;
import UMC.WithYou.repository.PackingItemRepository;
import UMC.WithYou.repository.dummy.DummyTravelRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PackingItemService {
    private final PackingItemRepository packingItemRepository;
    private final DummyTravelRepository travelRepository;
    private final DummyMemberRepository memberRepository;


    public PackingItemService(PackingItemRepository packingItemRepository, DummyTravelRepository travelRepository, DummyMemberRepository memberRepository){
        this.packingItemRepository = packingItemRepository;
        this.travelRepository = travelRepository;
        this.memberRepository = memberRepository;
    }

    public PackingItem addPackingItem(Long travelId, String itemName) {
        DummyTravel travel = travelRepository.findById(travelId).get();
        PackingItem packingItem =  PackingItem.createPackingItem(travel, itemName);

        return packingItemRepository.save(packingItem);
    }

    public List<PackingItem> findPackingItems(Long travelId){
        return packingItemRepository.findByTravelId(travelId);
    }

    public void deletePackingItem(Long id){
        PackingItem packingItem = packingItemRepository.findById(id).get();
        packingItemRepository.delete(packingItem);
    }

    public Boolean setPacker(Long packingItemId, Long packerId){
        PackingItem packingItem = packingItemRepository.findById(packingItemId).get();
        DummyMember packer = memberRepository.findById(packerId).get();
        if (packerId.equals(packer.getId())){
            if (packingItem.getIsChecked()) {
                packingItem.toggleCheckbox();
            }
            packer = null;
        }
        packingItem.setPacker(packer);
        return packingItem.getIsChecked();
    }

    public Boolean toggleCheckbox(Long packingItemId) {
        PackingItem packingItem = packingItemRepository.findById(packingItemId).get();

        if (packingItem.isPackerChosen()){
            return packingItem.getIsChecked();
        }
        return packingItem.toggleCheckbox();
    }
}
