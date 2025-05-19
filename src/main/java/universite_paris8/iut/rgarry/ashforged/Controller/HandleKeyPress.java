package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

public class HandleKeyPress {
    public static void handle(KeyEvent event, Personnage personnage) {

        final double MOVE_SPEED = personnage.getVitesse();

        if (event.getCode() == KeyCode.SPACE) {
            personnage.deplacer('u');

        }
        if (event.getCode() == KeyCode.S) {
            personnage.deplacer('d');
        }
        if (event.getCode() == KeyCode.Q) {
            personnage.deplacer('l');
        }
        if (event.getCode() == KeyCode.D) {
            personnage.deplacer('r');
        }
        if (event.getCode() == KeyCode.I) {
            System.out.println("Touche I Pressée");
            personnage.getInventory();
        }
    }
}
