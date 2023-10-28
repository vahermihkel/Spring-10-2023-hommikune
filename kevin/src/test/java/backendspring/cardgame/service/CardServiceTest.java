package backendspring.cardgame.service;

import backendspring.cardgame.model.GuessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    CardService cardService;

    String[] suits = { "S", "H", "D", "C" };
    String[] nums = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

    @BeforeEach
    void setUp() {
    }

    @Test
    void suitIsCorrectWhenGameStarts() {
        String card = cardService.start();
        System.out.println(card);
        boolean suitInCardIsOneOfTheSuits = Arrays.stream(suits).anyMatch( e -> e.equals(card.split("")[0]) );
        assertTrue(suitInCardIsOneOfTheSuits);
    }

    @Test
    void numIsCorrectWhenGameStarts() {
        String card = cardService.start();
        boolean numInCardIsOneOfTheNums = Arrays.stream(nums).anyMatch( e -> e.equals(card.split(" ")[0].substring(1)) );
        assertTrue(numInCardIsOneOfTheNums);
    }

    @Test
    void checkIfWrongWhenInvalidGuess() {
        cardService.start();
        GuessResponse guessResponse = cardService.checkIfCorrect("invalid");
        assertEquals("Wrong", guessResponse.getMessage());
    }

    @Test
    void checkIfTimeout() throws InterruptedException {
        cardService.start();
        Thread.sleep(11001);
        String message = cardService.checkIfTimeout();
        assertEquals("TIME_OVER!", message);
    }

    @Test
    void getRandomCard() {
        int cardRank = cardService.getRandomCard().getRank();
        boolean isRankCorrect = cardRank >= 2 && cardRank <= 10;
        assertTrue(isRankCorrect);
    }

    @Test
    void getScore() {
        int pointsAtBeginning = cardService.getScore();
        assertEquals(0, pointsAtBeginning);
    }

    @Test
    void checkIfCardDeckLengthIsCorrect() {
        int deckSize = cardService.getCardDeck().size();
        assertEquals(52, deckSize);
    }
}
