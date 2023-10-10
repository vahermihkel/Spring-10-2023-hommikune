package world;

import characters.Character;
import items.Item;

import java.util.List;

public class World {
    private int height;
    private int width;

    public World(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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
                    if (i.getxCoordinate() == x && i.getyCoordinate() == y) {
                        symbol = i.getSymbol();
                        break;
                    }
                }
                for (Character c: characters) {
                    if (c.getxCoordinate() == x && c.getyCoordinate() == y) {
                        symbol = c.getSymbol();
                        break;
                    }
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
