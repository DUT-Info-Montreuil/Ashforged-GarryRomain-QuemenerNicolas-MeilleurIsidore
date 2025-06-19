package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import universite_paris8.iut.rgarry.ashforged.Controller.CharacterController;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

/**
 * CharacterView is responsible for displaying the character sprite on the map,
 * binding it to the character's position and updating its image based on movement direction.
 */
public class CharacterView {
    private Pane paneperso;         // Pane on which the character is rendered
    private Image persoDroite;      // Character sprite facing right
    private Image persoGauche;      // Character sprite facing left
    private ImageView perso;        // Node representing the character on screen

    /**
     * Initializes the character's sprite on the map, binds its position,
     * and sets up key event handling for movement.
     *
     * @param paneperso the pane where the character is displayed
     * @param personnage the character model containing position data
     * @param personnageController the controller handling character input logic
     * @param field the field containing map dimensions
     */
    public CharacterView(Pane paneperso, Character personnage, CharacterController personnageController, Field field){
        this.paneperso = paneperso;

        // Set the pane size according to the map dimensions
        paneperso.setPrefSize(field.getWidth(), field.getHeight());

        // Load character images
        this.persoDroite = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/rightHero.png").toExternalForm());
        this.persoGauche = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/leftHero.png").toExternalForm());

        // Initialize with the right-facing sprite
        this.perso = new ImageView(persoDroite);
        perso.setId("perso");

        // Bind the character's visual position to its model coordinates
        perso.translateXProperty().bind(personnage.getXProperty());
        perso.translateYProperty().bind(personnage.getYProperty());

        // Add character to the pane and set focus for key events
        paneperso.getChildren().add(this.perso);
        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        // Attach keyboard handlers to manage movement
        personnageController.setupKeyHandlers(paneperso);
    }

    /**
     * Updates the character's sprite based on movement direction.
     *
     * @param direction 'r' for right, 'l' for left
     */
    public void changeSprite(char direction){
        if(direction == 'r'){
            perso.setImage(persoDroite);
        }
        else if(direction == 'l'){
            perso.setImage(persoGauche);
        }
    }
}
