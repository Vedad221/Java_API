package si.telekom.potres.controller;

import si.telekom.potres.model.Potres;
import si.telekom.potres.model.Vreme;
import si.telekom.potres.service.PotresiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import si.telekom.potres.service.VremeService;

@RestController
public class PotresiController {
    private final PotresiService potresiService;
    private final VremeService vremeService;

    @Autowired
    public PotresiController(PotresiService potresiService, VremeService vremeService) {
        this.potresiService = potresiService;
        this.vremeService = vremeService;

    }

    @GetMapping("/potresi/rekordi/mesecni")
    public Potres Mesec() {
        Potres rt;
        rt = potresiService.najdiZadnjiMesec();
        return rt;
    }

    @GetMapping("/potresi/rekordi/tedenski")
    public Potres Teden() {
        Potres rt;
        rt = potresiService.najdiZadnjiTeden();
        return rt;

    }
    @GetMapping("/test")
    public Potres zadnji(){

        Potres rt;
        rt = potresiService.najdiZadnji();
        return rt;

    }

}
