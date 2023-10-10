package characters;

import items.Item;
import world.World;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    // {lives: 3, symbol: "x", xCoord: 3, yCoord: 3, items: [{name: "SWORD", strength}, ...]}
//    int lives;
    List<Item> items;

    public Player(World world) {
        super('x', world);
        this.items = new ArrayList<>();
        health = 10.0;
    }

    public void addItem(Item item) {
        if (this.items.contains(item)) {
            item.increaseDuration();
        } else {
            items.add(item);
        }
//        for (item.Item i: this.items) {
//            System.out.println(i);
//        }
    }

    public void move(String input, World world) {
        switch (input) {
            case "w" -> {
                if (yCoordinate > 1) {
                    yCoordinate--;}
            }
            case "s" -> {
                if (yCoordinate < world.getHeight() - 2) {
                    yCoordinate++;}
            }
            case "a" -> {
                if (xCoordinate > 1) {
                    xCoordinate--;}
            }
            case "d" -> {
                if (xCoordinate < world.getWidth() - 2) {
                    xCoordinate++;}
            }
            default -> System.out.println("Use 'W', 'A', 'S', 'D' for moving around");
        }
    }

}
