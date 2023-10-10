package items;

import characters.Character;
import interfaces.Fight;
import interfaces.WorldObject;
import world.World;

import java.util.Random;

// extends <--- subclasside (alamklasside) jaoks      1x
// implements <--- interface-de jaoks (liideste jaoks)    lõpmatu arv

public class Item implements WorldObject, Fight {
    private ItemType name;
    private double strength;
    private int duration;
    private int xCoordinate;
    private int yCoordinate;
    private char symbol;

    public Item(World world, ItemType name) {
        this.name = name;
        switch (name) {
            case SWORD -> {
                this.strength = 10.0;
                this.duration = 1;
            }
            case HAMMER -> {
                this.strength = 5.0;
                this.duration = 3;
            }
            case BOOT -> {
                this.strength = 1.5;
                this.duration = 5;
            }
        }
        Random random = new Random(); // @Random312312312
        this.xCoordinate = generateRandomCoordinate(random, world.getWidth());
        this.yCoordinate = generateRandomCoordinate(random, world.getHeight());
        this.symbol = 'I';
    }

    public ItemType getName() {
        return name;
    }

    public double getStrength() {
        return strength;
    }

    public int getDuration() {
        return duration;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public char getSymbol() {
        return symbol;
    }

    public void increaseDuration() {
        this.duration++;
    }

    public void decreaseDuration() {
        this.duration--;
    }

    @Override
    public int generateRandomCoordinate(Random random, int bound) {
       return random.nextInt(1, bound - 1);
    }

    @Override
    public void hit(Character character) throws Exception {
        // VISATAKSE ALATI ENEMY SISSE PARAMEETRIS
        // character.health = character.health - strength;
        character.setHealth(character.getHealth() - strength);
    }
}
