package interfaces;

import java.util.Random;

public interface WorldObject {
    int generateRandomCoordinate(Random random, int bound);
}
