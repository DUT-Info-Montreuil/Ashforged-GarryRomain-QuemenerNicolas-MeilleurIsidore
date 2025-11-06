package universite_paris8.iut.rgarry.ashforged.model.ia;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;

public class NoMoveStrategy implements MovementStrategy {
    @Override
    public void move(Ennemis mob, Environment env) {
        // Intentionnellement vide (mob immobile)
    }
}
