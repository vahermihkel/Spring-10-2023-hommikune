package ee.tarvi.hiloproovit.model;


// "Korrektne. Järgmine kaart on: H5, sinu skoor on: 12"
// response.substring()
// {message: "Correct", card: "H5", score: 12}
// response.message   response.card    response.score

import lombok.Data;

@Data
public class GuessResponse {
    private String message;
    private String card;
    private int cardValue;
    private int score;
}
