import java.util.Random;

public class Item {
    ItemType name;
    double strength;
    int duration;
    int xCoordinate;
    int yCoordinate;
    char symbol;

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
        Random random = new Random();
        this.xCoordinate = random.nextInt(1, world.width - 1);
        this.yCoordinate = random.nextInt(1, world.height - 1);
        this.symbol = 'I';
    }
}
