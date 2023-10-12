package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.ToiduKomponent;
import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.entity.Toit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private List<Toiduaine> toiduained = new ArrayList<>();

    //localhost:8080/lisa-toiduaine?nimi=kartul&rasv=1&valk=2&sysivesik=16
    //localhost:8080/lisa-toiduaine?nimi=vorst&rasv=19&valk=12&sysivesik=4

    // @GetMapping("lisa-toiduaine/{nimi}/{rasv}/{valk}/{sysivesik}")
    // PathVariable varianti kasutades: localhost:8080/lisa-toiduaine/kartul/1/2/16
    @GetMapping("lisa-toiduaine") // localhost:8080/lisa-toiduaine?nimi=kartul&rasv=1&valk=2&sysivesik=16
    public List<Toiduaine> lisaToiduaine(
            @RequestParam String nimi,
            @RequestParam int rasv,
            @RequestParam int valk,
            @RequestParam int sysivesik
    ) throws Exception {
        Toiduaine toiduaine = new Toiduaine(nimi, valk, rasv, sysivesik);
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
        toiduained.add(toiduaine);
        return toiduained;
    }

    private List<ToiduKomponent> toiduKomponendid = new ArrayList<>();

    // localhost:8080/lisa-toidukomponent?toiduaineNimi=kartul&kogus=100
    @GetMapping("lisa-toidukomponent")
    public List<ToiduKomponent> lisaToidukomponent(
            @RequestParam String toiduaineNimi,
            @RequestParam int kogus
    ) throws Exception {
       Toiduaine toiduaine = null;
       for (Toiduaine t: toiduained) {
           if (toiduaineNimi.equals(t.getNimi())) {
               toiduaine = t;
               break;
           }
       }
       if (toiduaine == null) {
           throw new Exception("Toiduainet ei leitud!");
       }
       ToiduKomponent toiduKomponent = new ToiduKomponent(toiduKomponendid.size()+1 ,kogus, toiduaine);
       toiduKomponendid.add(toiduKomponent);
        return toiduKomponendid;
    }

    // http://localhost:8080/arvuta-komponendi-rasv?toiduaineNimi=kartul&kogus=200
    @GetMapping("arvuta-komponendi-rasv")
    public int arvutaKomponendiRasv(
            @RequestParam String toiduaineNimi,
            @RequestParam int kogus
    ) throws Exception {
        Toiduaine toiduaine = null;
        for (Toiduaine t: toiduained) {
            if (toiduaineNimi.equals(t.getNimi())) {
                toiduaine = t;
                break;
            }
        }
        if (toiduaine == null) {
            throw new Exception("Toiduainet ei leitud!");
        }
        return (int) (toiduaine.getRasv() / 100.0 * kogus);
    }
    // Integer.parseInt()
    // Double.parseDouble()
    // (int) 6.5

    private List<Toit> toidud = new ArrayList<>();

    // localhost:8080/lisa-toit?nimetus=kartulisalat&toiduKomponendid=1,2
    @GetMapping("lisa-toit")
    public List<Toit> lisaToit(
            @RequestParam String nimetus,
            @RequestParam int[] toiduKomponentideIds) {

        List<ToiduKomponent> toiduKomponendidToidus = new ArrayList<>();
        for (int id :toiduKomponentideIds) {
            for (ToiduKomponent t: toiduKomponendid) {
                if (id == t.getId()) {
                    toiduKomponendidToidus.add(t);
                }
            }
        }

        Toit toit = new Toit(nimetus, toiduKomponendidToidus);
        toidud.add(toit);
        return toidud;
    }

    // localhost:8080/toidu-valgud/kartulisalat
    @GetMapping("toidu-valgud/{nimetus}")
    public double arvutaToiduValgud(@PathVariable String nimetus) throws Exception {
        Toit toit = null;
        for (Toit t: toidud) {
            if (nimetus.equals(t.getNimetus())) {
                toit = t;
            }
        }
        if (toit == null) {
            throw new Exception("Toitu ei leitud!");
        }
        // HAKKAN SIIN OTSIMA VALKE
        return toit.saaValgud();
    }
}

