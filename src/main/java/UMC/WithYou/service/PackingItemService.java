package UMC.WithYou.service;

import UMC.WithYou.repository.PackingItermRepository;
import org.springframework.stereotype.Service;

@Service
public class PackingItemService {
    private PackingItermRepository packingItermRepository;

    public PackingItemService(PackingItermRepository packingItermRepository){
        this.packingItermRepository = packingItermRepository;
    }
}
