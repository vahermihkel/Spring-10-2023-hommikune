package ee.tarvi.hiloproovit.service;

import ee.tarvi.hiloproovit.entity.Game;
import ee.tarvi.hiloproovit.entity.Player;
import ee.tarvi.hiloproovit.model.GuessResponse;
import ee.tarvi.hiloproovit.repository.GameRepository;
import ee.tarvi.hiloproovit.repository.PlayerRepository;
import ee.tarvi.hiloproovit.util.RandomCard;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
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
        points = 0;
        lives = 3;
        baseCard = randomCard.getCard();
        startMove = System.currentTimeMillis();
        return baseCard + ":" + randomCard.cardValue(baseCard);
    }

    public GuessResponse checkIfCorrect(String guess) {
        GuessResponse guessResponse = new GuessResponse();
        if (lives <= 0) {
            guessResponse.setMessage("Game_Over");
            guessResponse.setScore(points);
            return guessResponse;
        }

        String resultCard = randomCard.getCard();
//        System.out.println(baseCard);
            log.info(baseCard);
//        System.out.println(randomCard.cardValue(baseCard));
        log.info(randomCard.cardValue(baseCard));
//        System.out.println(resultCard);
        log.info(resultCard);
//        System.out.println(randomCard.cardValue(resultCard));
        log.info(randomCard.cardValue(resultCard));
//        System.out.println(guess);
        log.info(guess);

        int baseCardValue = randomCard.cardValue(baseCard);
        int resultCardValue = randomCard.cardValue(baseCard);
        if (baseCardValue == resultCardValue && guess.equals("equal") ||
                baseCardValue > resultCardValue && guess.equals("lower") ||
                baseCardValue < resultCardValue && guess.equals("higher")) {
            points++;
            guessResponse.setMessage("Correct");
//            System.out.printf("Correct, it was %s! Points: %d %n", guess, points);
        } else {
            guessResponse.setMessage("Wrong");
            lives--;
            if (lives <= 0) {
                endGame();
                guessResponse.setMessage("Game_Over");
            }
//            System.out.println("Incorrect, it wasn't equal!");
        }
        baseCard = resultCard;
        guessResponse.setCardValue(resultCardValue);
        guessResponse.setCard(resultCard);
        guessResponse.setScore(points);
        return guessResponse;
    }

    private void endGame() {
        if (points > player.getHighScore()) {
            player.setHighScore(points);
            playerRepository.save(player);
        }
        Game game = Game.builder()
                .player(player)
                .creationDate(new Date())
                .points(points)
                .build();
        gameRepository.save(game);
//        List<Game> playerGames = player.getGames();
//        playerGames.add(game);
//        player.setGames(playerGames);
//        playerRepository.save(player);
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

//    public List<Player> getAll() {
//        return playerRepository.findAll();
//    }
}
