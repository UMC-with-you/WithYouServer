package UMC.WithYou.controller;

import UMC.WithYou.service.PackingItemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackingItemController {
    private PackingItemService packingItemService;

    public PackingItemController(PackingItemService packingItemService){
        this.packingItemService = packingItemService;
    }
}
