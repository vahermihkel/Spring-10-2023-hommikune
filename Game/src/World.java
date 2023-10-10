import java.util.List;

public class World {
    int height;
    int width;

    public World(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void printMap(List<Character> characters, List<Item> items) {
        char symbol;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // if (päringKasOnÕigused() || päringVeelKuhugi()) PRIMITIIV ENNE, LÄBIMINEV ENNE
                if (y == 0 || y == height - 1) {
                    symbol = '-';
                } else if (x == 0 || x == width - 1) {
                    symbol = '|';
                } else {
                    symbol = ' ';
                }
                // if (päringKasOnÕigused() && päringVeelKuhugi()) PRIMITIIV ENNE, FEILIV ENNE
//                if (playerXCoordinate == x && playerYCoordinate == y) {
//                    symbol = playerSymbol;
//                }
//                if (enemyXCoordinate == x && enemyYCoordinate == y) {
//                    symbol = enemySymbol;
//                }
                for (Item i: items) {
                    if (i.xCoordinate == x && i.yCoordinate == y) {
                        symbol = i.symbol;
                        break;
                    }
                }
                for (Character c: characters) {
                    if (c.xCoordinate == x && c.yCoordinate == y) {
                        symbol = c.symbol;
                        break;
                    }
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
