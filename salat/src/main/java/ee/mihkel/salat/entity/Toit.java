package ee.mihkel.salat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Toit {
    @Id
    private String nimetus;
    @ManyToMany
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
