import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        World world = new World(5, 8);



        Player player = new Player(world);
//        System.out.println(player.lives); // <-- kui on Character, siis on Characteri omadused
        Enemy enemy = new Enemy(world);
//        Character character = new Character(); <-- ei saa, kui on abstract
        List<Character> characters = new ArrayList<>();
        characters.add(player);
        characters.add(enemy);


        Item sword = new Item(world, ItemType.SWORD);
        Item hammer = new Item(world, ItemType.HAMMER);
        Item boot = new Item(world, ItemType.BOOT);
        List<Item> items = new ArrayList<>();
        items.add(sword);
        items.add(hammer);
        items.add(boot);


        // deklareerimine --> uue muutuja koos tema mälukoha välja kuulutamisega
        // initsialiseerimine --> esimest korda väärtuse andmine

        Scanner scanner = new Scanner(System.in);
//        ObjectMapper objectMapper = new ObjectMapper();
//        RestTemplate restTemplate = new RestTemplate();
//        Character player = new Player();
//        Character enemy = new Enemey();
//        Item sword = new Item();
//        Item dagger = new Item();

        world.printMap(characters, items);

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            player.move(input, world);

            world.printMap(characters, items);

            for (Item i: items) {
                if (i.xCoordinate == player.xCoordinate && i.yCoordinate == player.yCoordinate) {
                    player.addItem(i);
                }
            }

            input = scanner.nextLine();
        }

    }

    // private vs public <--- kas seda funktsiooni saab kasutada väljaspool seda klassi

}





//        int nr = 1;
//        Integer number = 2;
//        // klassi sees on kõik mis on ka primitiivis, aga lisaks funktsioonid
//        //   number.
//        char cr = 's';
//        boolean bl = true;
//        long lg = 2L;
//        double dbl = 2.0;
//        String str = "asdsa"; // suure tähega on klassid, igal klassil on mälukoht
//    == <--- võrreldakse ka mälukohta, klassidel on erinevad mälukohad
//   .equals() siis vaatab ainult sisu, mitte mälukohta

//        if (input.equals("w")) {
//            playerYCoordinate--;
//        } else if (input.equals(("s"))) {
//            playerYCoordinate++;
//        } else if (input.equals(("a"))) {
//            playerXCoordinate--;
//        } else if (input.equals(("d"))) {
//            playerXCoordinate++;
//        }
