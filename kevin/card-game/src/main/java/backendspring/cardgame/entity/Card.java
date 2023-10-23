package backendspring.cardgame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String name;
    private int rank;
}
