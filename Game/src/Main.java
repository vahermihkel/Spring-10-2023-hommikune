import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int playerXCoordinate = 3;
        int playerYCoordinate = 3;
        char playerSymbol = 'x';

        int enemyXCoordinate = 2;
        int enemyYCoordinate = 2;
        char enemySymbol = 'z';

        int worldHeight = 5;
        int worldWidth = 10;

        // deklareerimine --> uue muutuja koos tema mälukoha välja kuulutamisega
        // initsialiseerimine --> esimest korda väärtuse andmine

        Scanner scanner = new Scanner(System.in);
//        ObjectMapper objectMapper = new ObjectMapper();
//        RestTemplate restTemplate = new RestTemplate();
//        Character player = new Player();
//        Character enemy = new Enemey();
//        Item sword = new Item();
//        Item dagger = new Item();

        printMap(playerXCoordinate, playerYCoordinate, playerSymbol, enemyXCoordinate, enemyYCoordinate, enemySymbol, worldHeight, worldWidth);

        String input = scanner.nextLine();
        while (!input.equals("end")) {
            switch (input) {
                case "w" -> playerYCoordinate--;
                case "s" -> playerYCoordinate++;
                case "a" -> playerXCoordinate--;
                case "d" -> playerXCoordinate++;
            }

            printMap(playerXCoordinate, playerYCoordinate, playerSymbol, enemyXCoordinate, enemyYCoordinate, enemySymbol, worldHeight, worldWidth);
            input = scanner.nextLine();
        }

    }

    private static void printMap(int playerXCoordinate, int playerYCoordinate, char playerSymbol, int enemyXCoordinate, int enemyYCoordinate, char enemySymbol, int worldHeight, int worldWidth) {
        char symbol;
        for (int y = 0; y < worldHeight; y++) {
            for (int x = 0; x < worldWidth; x++) {
             // if (päringKasOnÕigused() || päringVeelKuhugi()) PRIMITIIV ENNE, LÄBIMINEV ENNE
                if (y == 0 || y == worldHeight - 1) {
                    symbol = '-';
                } else if (x == 0 || x == worldWidth - 1) {
                    symbol = '|';
                } else {
                    symbol = ' ';
                }
             // if (päringKasOnÕigused() && päringVeelKuhugi()) PRIMITIIV ENNE, FEILIV ENNE
                if (playerXCoordinate == x && playerYCoordinate == y) {
                    symbol = playerSymbol;
                }
                if (enemyXCoordinate == x && enemyYCoordinate == y) {
                    symbol = enemySymbol;
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
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
