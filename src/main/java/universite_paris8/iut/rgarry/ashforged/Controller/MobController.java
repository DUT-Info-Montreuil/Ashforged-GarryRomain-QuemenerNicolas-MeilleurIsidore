package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;

public class MobController {

    private final TilePane tilePane;
    private final Pane mobPane;

    private static Mobs mongolfière;
    private static Mobs soldat;
    private static Mobs zombie;
    private static Mobs bandit;
    private static Mobs boss;
    private static Mobs kozuki;

    /**
     * Constructeur du contrôleur de Mobs.
     *
     * @param tilePane TilePane pour afficher la liste ou grille des Mobs
     * @param panePerso Pane dédié à l'affichage des Mobs dans la scène
     */
    public MobController(TilePane tilePane, Pane panePerso) {
        this.tilePane = tilePane;
        this.mobPane = panePerso;
    }

    // TODO : Ajouter méthodes pour initialiser, afficher, gérer les mobs

}
