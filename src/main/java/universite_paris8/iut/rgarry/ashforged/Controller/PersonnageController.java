package universite_paris8.iut.rgarry.ashforged.Controller;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

public class PersonnageController {

    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private final BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private final Personnage personnage;
    private double velocityY = 0;
    private final double gravity = 0.5;
    private final int GROUND_LEVEL = 150;
    private final double jumpStrength = -15;

    public PersonnageController(Personnage personnage) {
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
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(false);
                case Q -> qPressed.set(false);
                case S -> sPressed.set(false);
                case D -> dPressed.set(false);
            }
        });
    }

    public void handleJump() {
        if (personnage.getY() == GROUND_LEVEL) {
            velocityY = jumpStrength;
        }
    }

    public void applyGravity() {
        velocityY += gravity;
        personnage.setY((int) (personnage.getY() + velocityY));

        if (personnage.getY() > GROUND_LEVEL) {
            personnage.setY(GROUND_LEVEL);
            velocityY = 0;
        }
    }

    public void handleLeft() {
        personnage.deplacer('l');
    }

    public void handleDown() {
        personnage.deplacer('d');
    }

    public void handleRight() {
        personnage.deplacer('r');
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