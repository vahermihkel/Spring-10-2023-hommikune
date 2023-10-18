package ee.mihkel.salat.entity;

//import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Builder
@Getter
//@Setter <-- automaatselt andmebaasi Hibernate alsuel, siis on vaja
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Toiduaine {
    @Id
    private String nimi;
    private int valk;
    private int rasv;
    private int sysivesik;

    public Toiduaine(String nimi, int valk, int rasv, int sysivesik) throws Exception {
        if (valk + rasv + sysivesik > 100) {
            throw new Exception("Protsent kokku ei saa ületada 100!");
        }
        this.nimi = nimi;
        this.valk = valk;
        this.rasv = rasv;
        this.sysivesik = sysivesik;
    }
}
