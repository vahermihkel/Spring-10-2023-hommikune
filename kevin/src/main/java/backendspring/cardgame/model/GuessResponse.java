package backendspring.cardgame.model;

import backendspring.cardgame.entity.Card;
import lombok.Data;

@Data // @Getter ja @Setter, @NoArgsConstructor
public class GuessResponse {
    private Card card;
    private String message;
    private int score;
}
