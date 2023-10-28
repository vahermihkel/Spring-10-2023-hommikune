package ee.tarvi.hiloproovit.controller;

import ee.tarvi.hiloproovit.entity.Player;
import ee.tarvi.hiloproovit.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseController {

    @Autowired
    CardService cardService;

// 1. Tagastatakse kõik mängijad
    @GetMapping("all-players")
    public List<Player> getAllPlayers() {
        return cardService.getAll();
    }

// 2. Tagastatakse kõik mängud
// 3. Tagatatakse kõik mängud points järjekorras
// 4. Tagatatakse kõik mängijad high-score järjekorras
// 5. Tagastataske kõik selle mängija mängud
// 6. Tagastataske kõik selle mängija mängud points järjekorras
// 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
// 8. Kõige suurema pointsidega mäng
// 9. Kõige suurema highscore-ga mängija
// 10. Top3 points mängud
}
