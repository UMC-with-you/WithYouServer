package UMC.WithYou.service;

import UMC.WithYou.repository.PackingItemRepository;
import org.springframework.stereotype.Service;

@Service
public class PackingItemService {
    private PackingItemRepository packingItermRepository;

    public PackingItemService(PackingItemRepository packingItermRepository){
        this.packingItermRepository = packingItermRepository;
    }
}
