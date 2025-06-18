package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.view.CharacterView;

import java.util.ArrayList;
import java.util.List;

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
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(true);
                case Q -> qPressed.set(true);
                case S -> sPressed.set(true);
                case D -> dPressed.set(true);
            }
            changerDirectionPersonnage();
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(false);
                case Q -> qPressed.set(false);
                case S -> sPressed.set(false);
                case D -> dPressed.set(false);
            }
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