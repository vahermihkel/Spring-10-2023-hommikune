import java.util.Random;

public abstract class Character {
    int xCoordinate;
    int yCoordinate;
    char symbol;

    public Character(char symbol, World world) {
//        World world = new World(5, 10);
        Random random = new Random();
        this.xCoordinate = random.nextInt(1, world.width - 1);
        this.yCoordinate = random.nextInt(1, world.height - 1);
        this.symbol = symbol;
    }

//    public void move(String input) {}

}
