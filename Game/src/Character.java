public abstract class Character {
    int xCoordinate = 2;
    int yCoordinate = 2;
    char symbol = 'z';

    public Character(char symbol) {
        this.xCoordinate = 3; // random number generator
        this.yCoordinate = 3; // random number generator
        this.symbol = symbol;
    }

//    public void move(String input) {}

}
