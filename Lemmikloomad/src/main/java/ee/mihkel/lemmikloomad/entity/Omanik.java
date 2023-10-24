package ee.mihkel.lemmikloomad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class Omanik {
    @Id
    private String nimi;

    @OneToMany
    private List<Lemmikloom> lemmikloomad;
}
