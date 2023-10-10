package characters;

import interfaces.WorldObject;
import world.World;

import java.util.Random;

public abstract class Character implements WorldObject {
    protected int xCoordinate;
    protected int yCoordinate;
    private char symbol;
    protected double health;

    public Character(char symbol, World world) {
//        world.World world = new world.World(5, 10);
        Random random = new Random();
        this.xCoordinate = generateRandomCoordinate(random, world.getWidth());
        this.yCoordinate = generateRandomCoordinate(random, world.getHeight());
        this.symbol = symbol;
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

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) throws Exception {
        if (this.health > 10.0) {
            throw new Exception("Health cannot be larger than 10!!");
        }
        this.health = health;
    }

    //    public void move(String input) {}

    @Override
    public int generateRandomCoordinate(Random random, int bound) {
        return random.nextInt(1, bound - 1);
    }

}
