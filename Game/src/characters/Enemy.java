package characters;

import interfaces.Fight;
import world.World;

public class Enemy extends Character implements Fight {
    double strength;

    public Enemy(World world) {
        super('z', world);
        this.strength = 2.0;
        health = 5.0;
    }

    @Override
    public void hit(Character character) throws Exception {
        // VISATAKSE ALATI PLAYER SISSE PARAMEETRIS
        character.setHealth(character.getHealth() - strength);
    }

//    public void move(String input) {}
}
