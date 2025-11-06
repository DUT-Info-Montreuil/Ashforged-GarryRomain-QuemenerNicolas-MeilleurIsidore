package universite_paris8.iut.rgarry.ashforged.model.ia;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;

public interface MovementStrategy {
    void move(Ennemis mob, Environment env);
}
