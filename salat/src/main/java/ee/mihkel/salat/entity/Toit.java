package ee.mihkel.salat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Toit {
    private String nimetus;
    private List<ToiduKomponent> toiduKomponendid;

    public double saaValgud() {
        double valgud = 0;
        for (ToiduKomponent t: toiduKomponendid) {
            valgud += t.getToiduaine().getValk() / 100.0 * t.getKogus();
        }
        return valgud;
    }

    public double saaRasvad() {
        double rasvad = 0;
        for (ToiduKomponent t: toiduKomponendid) {
            rasvad += t.getToiduaine().getRasv() / 100.0 * t.getKogus();
        }
        return rasvad;
    }
}
