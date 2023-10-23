package backendspring.cardgame.controller;

import backendspring.cardgame.model.GuessResponse;
import backendspring.cardgame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/start")
    public String startGame() {
        return cardService.start();
    }

    @GetMapping("/higher")
    public GuessResponse guessHigher() {
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
    public GuessResponse guessLower() {
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
    public GuessResponse guessEquals() {
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


