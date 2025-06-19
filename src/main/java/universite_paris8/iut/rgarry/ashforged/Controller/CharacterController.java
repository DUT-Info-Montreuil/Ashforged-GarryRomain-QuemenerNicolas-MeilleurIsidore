package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.KeyMapping;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class CharacterController {

    enum Direction {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private Character personnage;

    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private final BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private final double JUMP_STRENGHT = -12;

    private final TilePane tilePane;
    private final Pane panePerso;

    public CharacterController(TilePane tilePane, Pane panePerso, Character personnage) {
        this.tilePane = tilePane;
        this.panePerso = panePerso;
        this.personnage = personnage;
    }

    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyMapping.getKey("jump")) spacePressed.set(true);
            if (event.getCode() == KeyMapping.getKey("moveLeft")) qPressed.set(true);
            if (event.getCode() == KeyMapping.getKey("moveRight")) dPressed.set(true);
            if (event.getCode() == KeyMapping.getKey("down")) sPressed.set(true);
            changerDirectionPersonnage();
        });

        pane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyMapping.getKey("jump")) spacePressed.set(false);
            if (event.getCode() == KeyMapping.getKey("moveLeft")) qPressed.set(false);
            if (event.getCode() == KeyMapping.getKey("moveRight")) dPressed.set(false);
            if (event.getCode() == KeyMapping.getKey("down")) sPressed.set(false);
            changerDirectionPersonnage();
        });
    }

    public void changerDirectionPersonnage() {
        if (this.isQPressed()) {
            personnage.vaAGauche();
        }
        if (this.isDPressed()) {
            personnage.vaADroite();
        }
        if (!this.isQPressed() && !this.isDPressed()) {
            personnage.resteImobile();
        }
        // Vérifie la collision en bas à gauche ET en bas à droite avant d'autoriser le saut
        if (this.isSpacePressed() && personnage.getEnv().checkCollision(personnage.getX(), personnage.getY() + 32) && personnage.getEnv().checkCollision(personnage.getX() + 31, personnage.getY() + 32)) {
            personnage.setVelocityY(JUMP_STRENGHT);
        }
    }

    public boolean isSpacePressed() {
        return spacePressed.get();
    }

    public boolean isQPressed() {
        return qPressed.get();
    }

    public boolean isSPressed() {
        return sPressed.get();
    }

    public boolean isDPressed() {
        return dPressed.get();
    }


}