package ee.tarvi.hiloproovit.service;

import ee.tarvi.hiloproovit.entity.Game;
import ee.tarvi.hiloproovit.entity.Player;
import ee.tarvi.hiloproovit.model.GuessResponse;
import ee.tarvi.hiloproovit.repository.GameRepository;
import ee.tarvi.hiloproovit.repository.PlayerRepository;
import ee.tarvi.hiloproovit.util.RandomCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired // Dependency Injection
    RandomCard randomCard;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;
    Player player;
    String baseCard;
    int points;
    long timeoutMillis = 10000;
    long startMove;
    int lives = 3;

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
            lives--;
            if (lives == 0) {
                endGame();
            }
//            System.out.println("Incorrect, it wasn't equal!");
        }
        guessResponse.setCard(resultCard);
        guessResponse.setScore(points);
        return guessResponse;
    }

    private void endGame() {
        Game game = Game.builder()
                .player(player)
                .creationDate(new Date())
                .points(points)
                .build();
        gameRepository.save(game);
    }

    public int getScore() {
        return points;
    }

    public String checkIfTimeout() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - startMove > timeoutMillis) {
            return "TIMED_OUT!";
        } else {
            startMove = currentTime;
            return "";
        }
    }

    public void getPlayer(String playerName) {
        Optional<Player> playerOptional = playerRepository.findById(playerName);
        if (playerOptional.isPresent()) {
            player = playerOptional.get();
        } else {
            player = new Player(playerName, new Date(), 0);
            playerRepository.save(player);
        }
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }
}
