public class Player extends Character {
    int lives;

    public Player() {
        super('x');
    }

    public void move(String input) {
        switch (input) {
            case "w" -> yCoordinate--;
            case "s" -> yCoordinate++;
            case "a" -> xCoordinate--;
            case "d" -> xCoordinate++;
        }
    }
}