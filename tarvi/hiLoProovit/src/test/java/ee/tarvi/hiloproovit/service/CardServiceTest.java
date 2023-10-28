package ee.tarvi.hiloproovit.service;

import ee.tarvi.hiloproovit.model.GuessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    CardService cardService;

    String[] suits = {"♥", "♣", "♠", "♦"};
    String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    @BeforeEach
    void setUp() {

    }

    @Test
    void startGameReturnCardWithCorrectSuit() {
        String card = cardService.startGame();
        boolean isCardSuitOneOfTheCorrectSuits = Arrays.stream(suits).anyMatch(e -> e.equals(card.split("")[0]));
        assertTrue(isCardSuitOneOfTheCorrectSuits);
    }

    @Test
    void startGameReturnCardWithCorrectNum() {
        String card = cardService.startGame();
        boolean isCardNumOneOfTheCorrectNums = Arrays.stream(nums).anyMatch(e -> e.equals(card.substring(1)));
        assertTrue(isCardNumOneOfTheCorrectNums);
    }

    @Test
    void checkIfWrongWhenInvalidGuess() {
        cardService.startGame();
        GuessResponse guessResponse = cardService.checkIfCorrect("invalid");
        assertEquals("Wrong", guessResponse.getMessage());
    }

    @Test
    void getScore() {
        int pointsAtBeggining = cardService.getScore();
        assertEquals(0, pointsAtBeggining);
    }

    @Test
    void checkIfTimeout() throws InterruptedException {
        cardService.startGame();
        Thread.sleep(11001);
        String message = cardService.checkIfTimeout();
        assertEquals("TIMED_OUT!", message);
    }
}
