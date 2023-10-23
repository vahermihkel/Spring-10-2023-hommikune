package backendspring.cardgame.service;

import backendspring.cardgame.entity.Card;
import backendspring.cardgame.model.GuessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CardService {
    @Autowired
    Random random;

    List<Card> cards;
    long startTime;
    Card baseCard;
    int points = 0;

    public String start() {
        cards = new ArrayList<>(getCardDeck());
        points = 0;
        baseCard = getRandomCard();
        startTime = System.currentTimeMillis();

        return baseCard.getName() + " " + baseCard.getRank();
    }

    public GuessResponse checkIfCorrect(String guess) {
        GuessResponse guessResponse = new GuessResponse();
        Card card = getRandomCard();
        String response = "The next card is: " + card.getName() + " with a rank of " + card.getRank();
        if(card.getRank() > baseCard.getRank() && guess.equals("higher") ||
                card.getRank() < baseCard.getRank() && guess.equals("lower") ||
                card.getRank() == baseCard.getRank() && guess.equals("equal")
        ) {
            points++;
            baseCard = card;
            guessResponse.setCard(baseCard);
            guessResponse.setScore(points);
            guessResponse.setMessage("Correct");
            return guessResponse;
        } else {
            guessResponse.setCard(baseCard);
            guessResponse.setScore(points);
            guessResponse.setMessage("Wrong");
            return guessResponse;
        }
    }



    public String checkIfTimeout() {
        long endTime = System.currentTimeMillis();
        if(endTime - startTime > 10000) {
            return "TIME_OVER!";
        } else {
            startTime = endTime;
            return "";
        }
    }

    public Card getRandomCard() {
//        Random random = new Random();
        int randomIndex = random.nextInt(cards.size());
        Card card = cards.get(randomIndex);
        cards.remove(randomIndex);

        return card;
    }

    public int getScore() {
        return points;
    }

    public List<Card> getCardDeck() {
        return Arrays.asList(
                new Card("C2", 2),
                new Card("C3", 3),
                new Card("C4", 4),
                new Card("C5", 5),
                new Card("C6", 6),
                new Card("C7", 7),
                new Card("C8", 8),
                new Card("C9", 9),
                new Card("C10", 10),
                new Card("CJ", 10),
                new Card("CQ", 10),
                new Card("CK", 10),
                new Card("CA", 10),
                new Card("H2", 2),
                new Card("H3", 3),
                new Card("H4", 4),
                new Card("H5", 5),
                new Card("H6", 6),
                new Card("H7", 7),
                new Card("H8", 8),
                new Card("H9", 9),
                new Card("H10", 10),
                new Card("HJ", 10),
                new Card("HQ", 10),
                new Card("HK", 10),
                new Card("HA", 10),
                new Card("D2", 2),
                new Card("D3", 3),
                new Card("D4", 4),
                new Card("D5", 5),
                new Card("D6", 6),
                new Card("D7", 7),
                new Card("D8", 8),
                new Card("D9", 9),
                new Card("D10", 10),
                new Card("DJ", 10),
                new Card("DQ", 10),
                new Card("DK", 10),
                new Card("DA", 10),
                new Card("S2", 2),
                new Card("S3", 3),
                new Card("S4", 4),
                new Card("S5", 5),
                new Card("S6", 6),
                new Card("S7", 7),
                new Card("S8", 8),
                new Card("S9", 9),
                new Card("S10", 10),
                new Card("SJ", 10),
                new Card("SQ", 10),
                new Card("SK", 10),
                new Card("SA", 10));
    }
}
