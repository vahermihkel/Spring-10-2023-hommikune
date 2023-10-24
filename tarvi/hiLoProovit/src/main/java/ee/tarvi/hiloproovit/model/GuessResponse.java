package ee.tarvi.hiloproovit.model;


// "Korrektne. Järgmine kaart on: H5, sinu skoor on: 12"
// response.substring()
// {message: "Correct", card: "H5", score: 12}
// response.message   response.card    response.score

//@Data
public class GuessResponse {
    private String message;
    private String card;
    private int score;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public String getCard() {
        return card;
    }

    public int getScore() {
        return score;
    }
}
