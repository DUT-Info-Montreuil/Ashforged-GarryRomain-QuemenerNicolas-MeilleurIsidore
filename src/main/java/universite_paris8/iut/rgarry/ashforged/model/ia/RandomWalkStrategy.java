package universite_paris8.iut.rgarry.ashforged.model.ia;

import java.util.Random;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;

public class RandomWalkStrategy implements MovementStrategy {
    private final Random random = new Random();

    @Override
    public void move(Ennemis mob, Environment env) {
        // Change occasionnellement de direction
        if (random.nextInt(10) == 0) {
            mob.choisirDirectionAleatoire();
        }
        // Ne se deplace pas
    }
}
