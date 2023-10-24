package ee.tarvi.hiloproovit.service;

import ee.tarvi.hiloproovit.model.GuessResponse;
import ee.tarvi.hiloproovit.util.RandomCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired // Dependency Injection
    RandomCard randomCard;
    String baseCard;
    int points;
    long timeoutMillis = 10000;
    long startMove;

    public String startGame() {
        baseCard = randomCard.getCard();
        startMove = System.currentTimeMillis();
        return baseCard;
    }

    public GuessResponse checkIfCorrect(String guess) {
        GuessResponse guessResponse = new GuessResponse();

        String resultCard = randomCard.getCard();

        if (randomCard.cardValue(baseCard) == randomCard.cardValue(resultCard) && guess.equals("equal") ||
                randomCard.cardValue(baseCard) > randomCard.cardValue(resultCard) && guess.equals("lower") ||
                randomCard.cardValue(baseCard) < randomCard.cardValue(resultCard) && guess.equals("higher")) {
            points++;
            guessResponse.setMessage("Correct");
//            System.out.printf("Correct, it was %s! Points: %d %n", guess, points);
        } else {
            guessResponse.setMessage("Wrong");
//            System.out.println("Incorrect, it wasn't equal!");
        }
        guessResponse.setCard(resultCard);
        guessResponse.setScore(points);
        return guessResponse;
    }

    public int getScore() {
        return points;
    }

    public String checkIfTimeout() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - startMove > timeoutMillis) {
            return "TIMED_OUT! Points = " + points;
        } else {
            startMove = currentTime;
            return "";
        }
    }
}
