package ee.tarvi.hiloproovit.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

// util - funktsioonide klass, mida saan korduvkasutada
// service - funktsioonide klass, mida kasutatakse kindlas controlleris

//

@Component
public class RandomCard {

    @Autowired
    Random random;

    public String getCard() {

//        Random random = new Random();
        System.out.println(random);
        String[] suits = {"♥", "♣", "♠", "♦"};
        String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        int pickRandomNumber = random.nextInt(nums.length);
        int pickRandomSuite = random.nextInt(suits.length);
        String randomSuits = suits[pickRandomSuite];
        String randomNums = nums[pickRandomNumber];

        return randomSuits + randomNums;
    }

    public int cardValue(String card){
        String number = card.split("")[1];
        return switch (number) {
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "1","J","Q","K","A" -> 10; // 1 is for 10
            default -> 0;
        };
    }
}
