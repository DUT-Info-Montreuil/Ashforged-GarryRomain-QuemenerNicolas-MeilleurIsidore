package universite_paris8.iut.rgarry.ashforged.model.Projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;

/**
 * Représente une flèche dans le jeu.
 * Cette classe gère le mouvement, les collisions et les interactions de la flèche avec l'environnement et les entités.
 */
public class Arrow {
    // Constantes
    private static final double GRAVITY = 0.1; // Accélération due à la gravité

    private Environment environment; // Référence à l'environnement du jeu
    private int damageFleche = 10; // Dégâts infligés par la flèche

    // Position
    private IntegerProperty x; // Position horizontale de la flèche (propriété observable)
    private IntegerProperty y; // Position verticale de la flèche (propriété observable)

    // Vitesse
    private double velocityX; // Vitesse horizontale de la flèche
    private double velocityY; // Vitesse verticale de la flèche

    // État
    private boolean isActive; // Indique si la flèche est active et doit être mise à jour
    private boolean touchee = false;

    private ImageView arrowImage = new ImageView(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/arrow.png").toExternalForm()); // L'image de la flèche pour l'affichage





    /**
     * Constructeur de la classe Arrow.
     *
     * @param startX      La position X initiale de la flèche.
     * @param startY      La position Y initiale de la flèche.
     * @param environment L'environnement du jeu.
     */
    public Arrow(double startX, double startY, Environment environment) {
        this.x = new SimpleIntegerProperty((int) startX);
        this.y = new SimpleIntegerProperty((int) startY);
        this.velocityX = 3;
        this.velocityY = -5;
        this.isActive = true;
        this.environment = environment;
    }

    /**
     * Met à jour l'état de la flèche à chaque frame.
     * Gère le mouvement, l'application de la gravité et les collisions.
     */
    public void update() {
        if (!isActive) return; // Si la flèche n'est pas active, ne rien faire

        moveHorizontal(); // Déplace la flèche horizontalement
        applyGravity(); // Applique la gravité à la flèche
        attackFleche(); // Vérifie si la flèche touche une entité et lui inflige des dégâts
    }

    /**
     * Déplace la flèche horizontalement.
     * Gère les collisions avec les éléments de l'environnement.
     */
    private void moveHorizontal() {
        double newX = x.get() + velocityX; // Calcule la nouvelle position X

        if (!environment.getField().checkCollision((int) newX, y.get())) {
            x.set((int) newX); // Si pas de collision, met à jour la position X
        } else {
            velocityX = 0; // Si collision, arrête la flèche horizontalement
            isActive = false; // Désactive la flèche
        }
    }

    /**
     * Applique la gravité à la flèche.
     * Gère les collisions avec le sol.
     */
    private void applyGravity() {
        velocityY += GRAVITY; // Augmente la vitesse verticale (gravité)

        int steps = (int) Math.abs(velocityY); // Nombre d'étapes pour le déplacement vertical
        int direction = velocityY > 0 ? 1 : -1; // Direction du déplacement vertical (vers le bas ou vers le haut)

        for (int i = 0; i < steps; i++) {
            int nextY = y.get() + direction; // Calcule la prochaine position Y

            if (!environment.getField().checkCollision(x.get(), nextY)) {
                y.set(nextY); // Si pas de collision, met à jour la position Y
            } else {
                velocityY = 0; // Si collision, arrête la flèche verticalement
                isActive = false; // Désactive la flèche
                break; // Sort de la boucle
            }
        }
    }

    /**
     * Gère l'attaque de la flèche.
     * Vérifie si la flèche touche une entité et lui inflige des dégâts.
     */
    public void attackFleche() {
        for (Entity entity : environment.getEntities()) {
            if(entity instanceof Mobs) {
                int dx = Math.abs(entity.getX() / 64 - this.getX() / 64);
                int dy = Math.abs(entity.getY() / 64 - this.getY() / 64);
                if (dx == 0 && dy == 0) {
                    System.out.println("touche !");
                    int damage;
                    if (environment.getHero().getStats()[1] > 1) damage = (int) (environment.getHero().getStats()[1] * 0.5 + ((double) damageFleche / 2));
                    else damage = damageFleche / 2;
                    if(entity.getHealth() - damage <= 0) {
                        entity.setHealth(0);
                        System.out.println("Vous avez tué " + entity.getName() + " avec une fleche !");
                        System.out.println(entity.getLevel());
                        environment.getHero().gainExp(entity.getLevel());
                    } else {
                        entity.setHealth(entity.getHealth() - damage);
                        System.out.println("Vous avez infligé " + damage + " points de dégâts à " + entity.getName() + " !");
                    }
                    isActive = false;
                }
            }
        }
    }

    // Getters
    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }

    public boolean isActive() {
        return isActive;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    // Setters
    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setArrowImage(ImageView arrowImage) {
        this.arrowImage=arrowImage;
    }

    public ImageView getArrowImage() {
        return arrowImage;
    }
}
