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

// Class to manage the graphical representation of a character on the game map
public class CharacterView {
    private Pane characterPane; // Pane to hold the character's sprite
    private Image rightSprite; // Image for the character facing right
    private Image leftSprite; // Image for the character facing left
    private ImageView characterSprite; // ImageView for the character's sprite

    /**
     * Constructor for CharacterView, initializes the character's sprite on the map
     * and binds its position to the character's properties.
     *
     * @param characterPane The Pane where the character's sprite is displayed.
     * @param character The Character model whose position and sprite are managed.
     * @param characterController The controller handling character input events.
     * @param field The game field providing map dimensions.
     */
    public CharacterView(Pane characterPane, Character character, CharacterController characterController, Field field) {
        this.characterPane = characterPane; // Store the pane reference

        // Set the pane size based on field dimensions
        characterPane.setPrefSize(field.getWidth(), field.getHeight());

        // Load character sprite images
        this.rightSprite = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/rightHero.png").toExternalForm());
        this.leftSprite = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/leftHero.png").toExternalForm());
        this.characterSprite = new ImageView(rightSprite); // Initialize with right-facing sprite

        // Set ID for the character sprite
        characterSprite.setId("character");

        // Bind sprite position to character's X and Y properties
        characterSprite.translateXProperty().bind(character.getXProperty());
        characterSprite.translateYProperty().bind(character.getYProperty());

        // Add sprite to the pane
        characterPane.getChildren().add(this.characterSprite);
        // Enable focus for the pane to handle key events
        characterPane.setFocusTraversable(true);
        characterPane.requestFocus();

        // Set up key event handlers via the controller
        characterController.setupKeyHandlers(characterPane);
    }

    /**
     * Changes the character's sprite based on the direction of movement.
     *
     * @param direction The direction of movement ('r' for right, 'l' for left).
     */
    public void changeSprite(char direction) {
        // Update sprite image based on direction
        if (direction == 'r') {
            characterSprite.setImage(rightSprite); // Set right-facing sprite
        } else if (direction == 'l') {
            characterSprite.setImage(leftSprite); // Set left-facing sprite
        }
    }
}