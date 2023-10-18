package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.ToiduKomponent;
import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.entity.Toit;
import ee.mihkel.salat.repository.ToiduKomponentRepository;
import ee.mihkel.salat.repository.ToiduaineRepository;
import ee.mihkel.salat.repository.ToitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalatController {
//    private List<Toiduaine> toiduained = new ArrayList<>();

    @Autowired
    ToiduaineRepository toiduaineRepository;

    @Autowired
    ToiduKomponentRepository toiduKomponentRepository;

    @Autowired
    ToitRepository toitRepository;
    // Võimekuse saada ToiduaineRepository otseühendust siia klassi
    // Tagataustal tekitatakse justkui new Repository() ja ei tekitata seda korduvalt

    //localhost:8080/lisa-toiduaine?nimi=kartul&rasv=1&valk=2&sysivesik=16
    //localhost:8080/lisa-toiduaine?nimi=vorst&rasv=19&valk=12&sysivesik=4

    // @GetMapping("lisa-toiduaine/{nimi}/{rasv}/{valk}/{sysivesik}")
    // PathVariable varianti kasutades: localhost:8080/lisa-toiduaine/kartul/1/2/16
    @PostMapping("lisa-toiduaine") // localhost:8080/lisa-toiduaine?nimi=kartul&rasv=1&valk=2&sysivesik=16
    public List<Toiduaine> lisaToiduaine(
            @RequestParam String nimi,
            @RequestParam int rasv,
            @RequestParam int valk,
            @RequestParam int sysivesik
    ) throws Exception {
        // RequestBody tehakse siis kui täpselt see mudel tuleb sisse millele ma peaksin muidu contructori tegema

         Toiduaine toiduaine = new Toiduaine(nimi, valk, rasv, sysivesik);

//        Toiduaine toiduaine = tAine;
//        Toiduaine.builder()
//                .nimi(nimi)
//                .rasv(rasv)
//                .valk(valk)
//                .sysivesik(sysivesik)
//                .build();
        //        toiduaine.setNimi(nimi);
//        toiduaine.setNimi(rasv);
//        toiduaine.setNimi(valk);
//        toiduaine.setNimi(sysivesik);
//        toiduained.add(toiduaine);
        toiduaineRepository.save(toiduaine);
        return toiduaineRepository.findAll();
    }

//    private List<ToiduKomponent> toiduKomponendid = new ArrayList<>();

    // localhost:8080/lisa-toidukomponent?toiduaineNimi=kartul&kogus=100
    @PostMapping("lisa-toidukomponent")
    public List<ToiduKomponent> lisaToidukomponent(
            @RequestParam String toiduaineNimi,
            @RequestParam int kogus
            // @RequestBody Toidukomponent toidukomponent
    ) {
//       Toiduaine toiduaine = null;
//       for (Toiduaine t: toiduaineRepository.findAll()) {
//           if (toiduaineNimi.equals(t.getNimi())) {
//               toiduaine = t;
//               break;
//           }
//       }
//       if (toiduaine == null) {
//           throw new Exception("Toiduainet ei leitud!");
//       }
       Toiduaine toiduaine = toiduaineRepository.findById(toiduaineNimi).get();
//       ToiduKomponent toiduKomponent = new ToiduKomponent();
        ToiduKomponent toiduKomponent = ToiduKomponent.builder()
                .kogus(kogus)
                .toiduaine(toiduaine)
                .build();
//       toiduKomponendid.add(toiduKomponent);
        toiduKomponentRepository.save(toiduKomponent);
        return toiduKomponentRepository.findAll();
    }

    // http://localhost:8080/arvuta-komponendi-rasv?toiduaineNimi=kartul&kogus=200
    @GetMapping("arvuta-komponendi-rasv")
    public int arvutaKomponendiRasv(
            @RequestParam String toiduaineNimi,
            @RequestParam int kogus
    ) {
//        Toiduaine toiduaine = null;
//        for (Toiduaine t: toiduained) {
//            if (toiduaineNimi.equals(t.getNimi())) {
//                toiduaine = t;
//                break;
//            }
//        }
//        if (toiduaine == null) {
//            throw new Exception("Toiduainet ei leitud!");
//        }
        Toiduaine toiduaine = toiduaineRepository.findById(toiduaineNimi).get();
        return (int) (toiduaine.getRasv() / 100.0 * kogus);
    }
    // Integer.parseInt()
    // Double.parseDouble()
    // (int) 6.5

//    private List<Toit> toidud = new ArrayList<>();

    // localhost:8080/lisa-toit?nimetus=kartulisalat&toiduKomponentideIds=1,2
    @PostMapping("lisa-toit")
    public List<Toit> lisaToit(
            @RequestParam String nimetus,
            @RequestParam Integer[] toiduKomponentideIds) {

//        List<ToiduKomponent> toiduKomponendidToidus = new ArrayList<>();
//        for (int id :toiduKomponentideIds) {
//            for (ToiduKomponent t: toiduKomponendid) {
//                if (id == t.getId()) {
//                    toiduKomponendidToidus.add(t);
//                }
//            }
//        }                             // konvertimine arrayst [] ----> List<> kujule
        List<Integer> toiduKomponentideIdsListina = List.of(toiduKomponentideIds);
        List<ToiduKomponent> toiduKomponendidToidus = toiduKomponentRepository.findAllById(toiduKomponentideIdsListina);

        Toit toit = new Toit(nimetus, toiduKomponendidToidus);
//        toidud.add(toit);
        toitRepository.save(toit);
        return toitRepository.findAll();
    }

    // localhost:8080/toidu-valgud/kartulisalat
    @GetMapping("toidu-valgud/{nimetus}")
    public double arvutaToiduValgud(@PathVariable String nimetus) {
//        Toit toit = null;
//        for (Toit t: toidud) {
//            if (nimetus.equals(t.getNimetus())) {
//                toit = t;
//            }
//        }
//        if (toit == null) {
//            throw new Exception("Toitu ei leitud!");
//        }
        Toit toit = toitRepository.findById(nimetus).get();
        // HAKKAN SIIN OTSIMA VALKE
        return toit.saaValgud();
    }

//    @DeleteMapping <-- kustutamiseks

//    @PutMapping <-- muutmiseks

//    @PatchMapping <-- ühe kindla välja muutmine     API otspunkt, mis muudab broneerituks, ostetuks, kogust vähemaks
}

