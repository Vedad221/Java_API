package si.telekom.potres.controller;
import si.telekom.potres.model.Potres;
import si.telekom.potres.service.PotresiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotresiController {
    private final PotresiService potresiService;

    @Autowired
    public PotresiController(PotresiService potresiService) {
        this.potresiService = potresiService;
    }

    @GetMapping("/test")
    public Potres greeting() {
        Potres rt;
        rt = potresiService.najdiZadnjiMesec();
        return rt;
    }
}
