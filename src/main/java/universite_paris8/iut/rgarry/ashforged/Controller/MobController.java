package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;

/**
 * Controller for managing enemy mobs in the AshForged game.
 *
 * This class handles interactions related to hostile characters (mobs) such as displaying them,
 * managing their presence in the UI, or preparing data for combat scenarios.
 */
public class MobController {

    /** The UI tile pane where mobs or their related info can be displayed. */
    private final TilePane tilePane;

    /** The pane used for displaying or interacting with mob characters. */
    private final Pane mobPane;

    // Static instances representing different types of mobs in the game.
    private static Mobs mongolfière;
    private static Mobs soldat;
    private static Mobs zombie;
    private static Mobs bandit;
    private static Mobs boss;
    private static Mobs kozuki;

    /**
     * Constructs a MobController with the specified UI containers.
     *
     * @param tilePane the tile pane used for mob item or icon layout.
     * @param panePerso the pane dedicated to mob interactions or display.
     */
    public MobController(TilePane tilePane, Pane panePerso) {
        this.tilePane = tilePane;
        this.mobPane = panePerso;
    }

    // Future features could include:
    // - Loading and initializing mobs dynamically
    // - Handling mob combat logic or display
    // - Updating UI components based on mob states
}
