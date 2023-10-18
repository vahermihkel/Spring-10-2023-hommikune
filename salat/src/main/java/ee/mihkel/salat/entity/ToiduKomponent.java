package ee.mihkel.salat.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class ToiduKomponent {
    @Id
    // ->kogu aeg<- liidab 1-e juurde viimasega võrreldes
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int kogus;

    @ManyToOne
    private Toiduaine toiduaine;
}
