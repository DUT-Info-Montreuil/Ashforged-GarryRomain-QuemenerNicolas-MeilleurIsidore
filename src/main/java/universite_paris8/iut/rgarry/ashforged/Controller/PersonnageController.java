package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

public class PersonnageController {

    private static final String SKY_TILE_ID = "ciel";
    private static final int BOUNDARY_OFFSET = 3;

    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private final BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private static Personnage personnage;
    private static Npc paolo;
    private static Personnage branda;
    private static Personnage terry;
    private static Personnage salome;

    private static Mobs mongolfière;
    private static Mobs soldat;
    private static Mobs zombie;
    private static Mobs bandit;
    private static Mobs boss;
    private static Mobs kozuki;

    private double velocityY;
    private final double gravity = 0.5;
    private final int GROUND_LEVEL = 150;
    private final double jumpStrength = -15;

    private final TilePane tilePane;
    private final Pane panePerso;

    private enum Direction { LEFT, RIGHT, TOP, BOTTOM }

    private Mobs[] mobs;
    private Mobs[] mobsOnMap = new Mobs[30];

    public PersonnageController(TilePane tilePane, Pane panePerso) {
        this.personnage = new Personnage("Hero", 15, new int[]{1, 1, 10, 1}, 50, 300);
        this.tilePane = tilePane;
        this.panePerso = panePerso;

        // Creation of NPC
        this.paolo = new Npc("Paolo", 15, 100,  new int[]{1, 1, 10, 1}, "Hey listen", new ItemInterface[]{ItemStock.Weapon.bow, ItemStock.Usuable.coal}, 600, 250);
        this.branda = new Npc("Branda", 15, 100,  new int[]{1, 1, 10, 1}, "Hey listen", new ItemInterface[]{ItemStock.Usuable.golden_piece}, 600, 250);;
        this.terry = new Npc("Terry", 15, 100,  new int[]{1, 1, 10, 1}, "Hey listen", new ItemInterface[]{ItemStock.Weapon.bow, ItemStock.Usuable.coal}, 600, 250);
        this.salome = new Npc("Salome", 15, 100,  new int[]{1, 1, 10, 1}, "Hey listen", new ItemInterface[]{ItemStock.Weapon.bow, ItemStock.Usuable.coal}, 600, 250);

        mobs = new Mobs[] {
                this.mongolfière = new Mobs("Mongolfière", 15, 100, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.bomb, 600, 250),
                this.soldat = new Mobs("Soldat", 15, 100, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.stone_sword, 600, 250),
                this.zombie = new Mobs("Zombie", 15, 100, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.stick, 600, 250),
                this.bandit = new Mobs("Bandit", 15, 100, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.firearm, 600, 250),
                this.boss = new Mobs("Boss", 20, 100, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.steel_sabre, 600, 250),
        };
        this.kozuki = new Mobs("Kozuki", 30, 100, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.enma, 600, 250);

        mobsOnMap = new Mobs[30];
    }



    public static Personnage getPersonnage() {
        return personnage;
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

    public void applyGravity() {
        velocityY += GRAVITY;
        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            if (direction > 0 && !checkCollision(Direction.BOTTOM)) {
                personnage.setY(personnage.getY() + 1);
            } else if (direction < 0 && !checkCollision(Direction.TOP)) {
                personnage.setY(personnage.getY() - 1);
            } else {
                velocityY = 0;
                break;
            }
        }
    }

    private boolean checkCollision(Direction direction) {
        int deplacement = personnage.getVitesse();
        var persoNodes = panePerso.getChildren().stream()
                .filter(ImageView.class::isInstance)
                .map(ImageView.class::cast)
                .toList();
        var tileNodes = tilePane.getChildren().stream()
                .filter(ImageView.class::isInstance)
                .map(ImageView.class::cast)
                .filter(node -> !SKY_TILE_ID.equals(node.getId()))
                .toList();

        for (Node persoNode : persoNodes) {
            var bounds = persoNode.getBoundsInParent();
            double nextMinX = bounds.getMinX();
            double nextMaxX = bounds.getMaxX();
            double nextMinY = bounds.getMinY();
            double nextMaxY = bounds.getMaxY();

            switch (direction) {
                case LEFT -> nextMinX -= deplacement;
                case RIGHT -> nextMaxX += deplacement;
                case TOP -> nextMinY -= deplacement;
                case BOTTOM -> nextMaxY += deplacement;
            }

            for (Node tileNode : tileNodes) {
                var tileBounds = tileNode.getBoundsInParent();
                boolean overlapX = nextMaxX > tileBounds.getMinX() && nextMinX < tileBounds.getMaxX();
                boolean overlapY = nextMaxY > tileBounds.getMinY() && nextMinY < tileBounds.getMaxY();

                if (overlapX && overlapY) {
                    switch (direction) {
                        case LEFT -> {
                            personnage.setX((int) (tileBounds.getMaxX() + 1));
                            System.out.println("Collision LEFT");
                        }
                        case RIGHT -> {
                            personnage.setX((int) (tileBounds.getMinX() - bounds.getWidth()));
                            System.out.println("Collision RIGHT");
                        }
                        case TOP -> {
                            personnage.setY((int) tileBounds.getMaxY() + 1);
                            System.out.println("Collision TOP");
                        }
                        case BOTTOM -> {
                            personnage.setY((int) (tileBounds.getMinY() - bounds.getHeight()));
                            System.out.println("Collision BOTTOM");
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCollisionLeft()   { return checkCollision(Direction.LEFT); }
    public boolean checkCollisionRight()  { return checkCollision(Direction.RIGHT); }
    public boolean checkCollisionTop()    { return checkCollision(Direction.TOP); }
    public boolean checkCollisionBottom() { return checkCollision(Direction.BOTTOM); }

    public void handleJump() {
        if (checkCollisionBottom() && velocityY == 0) {
            velocityY = JUMP_STRENGTH;
        }
    }

    private int getMaxX() {
        return (int) tilePane.getWidth() + 1;
    }
    private int getMaxY() {
        return (int) tilePane.getHeight();
    }

    public void handleLeft() {
        if (!checkCollisionLeft()) {
            personnage.deplacer('l', getMaxX(), getMaxY());
        }
    }

    public void handleDown() {
        if (!checkCollisionBottom()) {
            personnage.deplacer('d', getMaxX(), getMaxY());
        }
    }

    public void handleRight() {
        double personnageWidth = panePerso.getChildren().stream()
                .filter(ImageView.class::isInstance)
                .mapToDouble(node -> node.getBoundsInParent().getWidth())
                .max().orElse(0);

        int maxX = getMaxX();
        if (!checkCollisionRight() && personnage.getX() + personnage.getVitesse() + personnageWidth <= maxX - BOUNDARY_OFFSET) {
            personnage.deplacer('r', maxX, getMaxY());
        } else if (personnage.getX() + personnageWidth > maxX - BOUNDARY_OFFSET) {
            personnage.setX((int) (maxX - personnageWidth));
        }
    }

    public boolean isSpacePressed() { return spacePressed.get(); }
    public boolean isQPressed()     { return qPressed.get(); }
    public boolean isSPressed()     { return sPressed.get(); }
    public boolean isDPressed()     { return dPressed.get(); }

    public void spawnMob(){
        for (int i = 0; i < mobsOnMap.length; i++) {
            if (mobsOnMap[i] == null) {
                mobsOnMap[i] = mobs[(int)(Math.random()* mobsOnMap.length)];
            }
        }
    }

}
