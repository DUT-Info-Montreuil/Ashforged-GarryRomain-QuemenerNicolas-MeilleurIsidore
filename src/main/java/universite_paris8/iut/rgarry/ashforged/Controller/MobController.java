package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;

public class MobController {

    private final TilePane tilePane;
    private final Pane mobPane;

    private static Ennemis mongolfière;
    private static Ennemis soldat;
    private static Ennemis zombie;
    private static Ennemis bandit;
    private static Ennemis boss;
    private static Ennemis kozuki;

    /**
     * Constructeur du contrôleur de Ennemis.
     *
     * @param tilePane TilePane pour afficher la liste ou grille des Ennemis
     * @param panePerso Pane dédié à l'affichage des Ennemis dans la scène
     */
    public MobController(TilePane tilePane, Pane panePerso) {
        this.tilePane = tilePane;
        this.mobPane = panePerso;
    }

    // TODO : Ajouter méthodes pour initialiser, afficher, gérer les Ennemis

}
