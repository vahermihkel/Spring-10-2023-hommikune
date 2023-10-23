package ee.tarvi.hiloproovit.controller;

import ee.tarvi.hiloproovit.service.CardService;
import ee.tarvi.hiloproovit.util.RandomCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/")
    public String startRoundRequest() {
       return cardService.startGame();
    }

    @GetMapping("/higher")
    public String optionHigher() {
        String response = cardService.checkIfTimeout();
        if (!response.equals("")) {
            return response;
        }

        response = cardService.checkIfCorrect("higher");
        return response;
    }

    @GetMapping("/lower")
    public String optionLower() {
        String response = cardService.checkIfTimeout();
        if (!response.equals("")) {
            return response;
        }

        response = cardService.checkIfCorrect("lower");
        return response;
    }

    @GetMapping("/equal")
    public String optionEqual() {
        String response = cardService.checkIfTimeout();
        if (!response.equals("")) {
            return response;
        }

        response = cardService.checkIfCorrect("equal");
        return response;
    }

}
