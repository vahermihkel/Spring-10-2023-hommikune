package ee.tarvi.hiloproovit.controller;

import ee.tarvi.hiloproovit.model.GuessResponse;
import ee.tarvi.hiloproovit.repository.PlayerRepository;
import ee.tarvi.hiloproovit.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/start")
    public String startRoundRequest(
            @RequestParam String playerName
    ) {
       cardService.getPlayer(playerName);
       return cardService.startGame();
    }

    @GetMapping("/higher")
    public GuessResponse optionHigher() {
        GuessResponse guessResponse = new GuessResponse();
        guessResponse.setScore(cardService.getScore());

        String response = cardService.checkIfTimeout();
        if (!response.equals("")) {
            guessResponse.setMessage(response);
            return guessResponse;
        }

        guessResponse = cardService.checkIfCorrect("higher");
        return guessResponse;
    }

    @GetMapping("/lower")
    public GuessResponse optionLower() {
        GuessResponse guessResponse = new GuessResponse();
        guessResponse.setScore(cardService.getScore());

        String response = cardService.checkIfTimeout();
        if (!response.equals("")) {
            guessResponse.setMessage(response);
            return guessResponse;
        }

        guessResponse = cardService.checkIfCorrect("lower");
        return guessResponse;
    }

    @GetMapping("/equal")
    public GuessResponse optionEqual() {
        GuessResponse guessResponse = new GuessResponse();
        guessResponse.setScore(cardService.getScore());

        String response = cardService.checkIfTimeout();
        if (!response.equals("")) {
            guessResponse.setMessage(response);
            return guessResponse;
        }

        guessResponse = cardService.checkIfCorrect("equal");
        return guessResponse;
    }

}
