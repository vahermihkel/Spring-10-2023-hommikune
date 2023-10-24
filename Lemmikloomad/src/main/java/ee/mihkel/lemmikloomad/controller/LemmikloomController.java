package ee.mihkel.lemmikloomad.controller;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import ee.mihkel.lemmikloomad.entity.Omanik;
import ee.mihkel.lemmikloomad.repository.LemmikloomRepository;
import ee.mihkel.lemmikloomad.repository.OmanikRepository;
import ee.mihkel.lemmikloomad.service.LemmikloomService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LemmikloomController {

    @Autowired
    LemmikloomRepository lemmikloomRepository;

    @Autowired
    OmanikRepository omanikRepository;

    @Autowired
    LemmikloomService lemmikloomService;

    // localhost:8080/lisa?nimetus=Koer&kaal=3.1
    @PostMapping("lisa")
    public List<Lemmikloom> lisaLemmikloom(
            @RequestParam String nimetus,
            @RequestParam double kaal,
            @RequestParam String omanikuNimi
    ) {
        lemmikloomService.lisaLoom(nimetus, kaal,omanikuNimi);

        return lemmikloomRepository.findAll();
    }

    // localhost:8080/omaniku-loomad?omanik=Mihkel
    @GetMapping("omaniku-loomad")
    public int saaOmanikuLoomadeArv(@RequestParam String omanik) {
        return omanikRepository.findById(omanik).orElseThrow().getLemmikloomad().size();
    }

    // localhost:8080/omaniku-suurima-kaaluga-loom?omanik=Mihkel
    @GetMapping("omaniku-suurima-kaaluga-loom")
    public Lemmikloom omanikuSuurimaKaalugaLoom(@RequestParam String omanik) {
        List <Lemmikloom> lemmikloomad = omanikRepository.findById(omanik).orElseThrow().getLemmikloomad();
        Lemmikloom suurim = lemmikloomad.get(0);
        for (Lemmikloom l: lemmikloomad) {
            if (l.getKaal() > suurim.getKaal()) {
                suurim = l;
            }
        }
        return suurim;
    }

    // Post päring, mis lisab Kliinikuid (name, List<Lemmikloom>)

//    @PostMapping("lisa-loom-kliinikusse")
//    public Kliinik lisaLoomKliinikusse(@RequestParam int loomaId, @RequestParam String nimi) {
//        Lemmikloom lemmikloom = lemmikloomRepository.findById(loomaId).orElseThrow();
//        Kliinik kliinik = kliinikRepository.findById(nimi).orElseThrow();
//
//        List<Lemmikloom> kliinikuLemmikloomad = kliinik.getLemmikloomad();
//        kliinikuLemmikloomad.add(lemmikloom);
//        kliinik.setLemmikloomad(kliinikuLemmikloomad);
//        kliinikRepository.save(kliinik);
//        return kliinik;
//    }

    // localhost:8080/suurima-kaaluga-loom
    @GetMapping("suurima-kaaluga-loom")
    public Lemmikloom suurimaKaalugaLoom() {
       return lemmikloomRepository.findFirstByOrderByKaalDesc();
    }

    @GetMapping("vahemiku-kaaluga-loom")
    public List<Lemmikloom> vahemikuKaalugaLoom(
            @RequestParam double min,
            @RequestParam double max
    ) {
        return lemmikloomRepository.findAllByKaalBetween(min,max);
    }
}
