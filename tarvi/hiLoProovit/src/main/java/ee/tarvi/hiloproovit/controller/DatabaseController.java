package ee.tarvi.hiloproovit.controller;

import ee.tarvi.hiloproovit.entity.Game;
import ee.tarvi.hiloproovit.entity.Player;
import ee.tarvi.hiloproovit.repository.GameRepository;
import ee.tarvi.hiloproovit.repository.PlayerRepository;
import ee.tarvi.hiloproovit.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DatabaseController {

//    @Autowired
//    CardService cardService;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

// 1. Tagastatakse kõik mängijad
    @GetMapping("all-players") // localhost:8080/all-players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

// 2. Tagastatakse kõik mängud
    @GetMapping("all-games")  // localhost:8080/all-games
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

// 3. Tagatatakse kõik mängud points järjekorras
    @GetMapping("all-games-ordered")  // localhost:8080/all-games-ordered
    public List<Game> getAllGamesOrdered() {
        return gameRepository.findAllByOrderByPointsDesc();
    }


// 4. Tagatatakse kõik mängijad high-score järjekorras
    @GetMapping("all-players-ordered")  // localhost:8080/all-games-ordered
    public List<Player> getAllPlayersOrdered() {
        return playerRepository.findAllByOrderByHighScoreDesc();
    }

// 5. Tagastataske kõik selle mängija mängud
    @GetMapping("all-games-by-player")  // localhost:8080/all-games-by-player?playerName=m
    public List<Game> getAllGamesByPlayer(
            @RequestParam String playerName
    ) {
        return gameRepository.findAllByPlayerName(playerName);
    }


// 6. Tagastataske kõik selle mängija mängud points järjekorras
    @GetMapping("all-games-by-player-ordered")  // localhost:8080/all-games-by-player-ordered
    public List<Game> getAllGamesByPlayerOrdered(
            @RequestParam String playerName
    ) {
        return gameRepository.findAllByPlayerNameOrderByPointsDesc(playerName);
    }

    // 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust

    @GetMapping("all-games-by-correct-anwsers") // localhost:8080/all-games-by-correct-anwsers
    public List<Game> getAllGamesByCorrectAnswers() {
        return gameRepository.findAllByPointsGreaterThanEqual(2);
    }

    @GetMapping("all-games-by-correct-anwsers2") // localhost:8080/all-games-by-correct-anwsers
    public List<Game> getAllGamesByCorrectAnswers2(
            @RequestParam int correctAnswers
    ) {
        return gameRepository.findAllByPointsGreaterThanEqual(correctAnswers);
    }

// 8. Kõige suurema pointsidega mäng
    @GetMapping("game-highest-points") // localhost:8080/game-highest-points
    public Game getGameWithHighestPoints() {
        return gameRepository.findFirstByOrderByPointsDesc();
    }

// 9. Kõige suurema highscore-ga mängija
    @GetMapping("player-highest-highscore") // localhost:8080/game-highest-points
    public Player getPlayerWithHighestHighscore() {
        return playerRepository.findFirstByOrderByHighScoreDesc();
    }

// 10. Top3 points mängud
// 11. Leia mängija mängu ID järgi

    @GetMapping("player-by-game-id") // localhost:8080/game-highest-points
    public Player getPlayerGameId(
            @RequestParam Long gameId
    ) {
        return gameRepository.findById(gameId).orElseThrow().getPlayer();
    }
}
