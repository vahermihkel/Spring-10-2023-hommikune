package ee.mihkel.salat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToiduKomponent {
    private int id;
    private int kogus;
    private Toiduaine toiduaine;
}
