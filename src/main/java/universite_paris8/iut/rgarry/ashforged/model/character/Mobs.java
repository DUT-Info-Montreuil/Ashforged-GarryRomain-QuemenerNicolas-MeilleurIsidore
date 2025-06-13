package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

import java.util.Random;

public class Mobs extends Character {
    private int stats_multiplier;
    private ItemInterface item;
    private final int initialX;
    private final Random random = new Random();
    int minX; // Position minimale pour l'axe
    int newX;
    int maxX;

    public Mobs(String name, int level, int[] stats, int stats_multiplier, ItemInterface item, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.stats_multiplier = stats_multiplier;
        this.item = item;
        this.initialX = x;
        minX = initialX - 640;
        maxX = initialX + 640;
    }

    private boolean aVuJoueur = false;

    private char directionCourante = 'i'; // 'g' = gauche, 'd' = droite, 'i' = immobile

    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        if (r == 0) directionCourante = 'g';
        else if (r == 1) directionCourante = 'd';
        else directionCourante = 'i';
    }

    @Override
    public void vaAGauche() {
        newX = getX() - getVitesse();
        if (!aVuJoueur) {
            if (newX < minX) newX = minX;
        }

        boolean collision = env.checkCollision(newX, getY()) || env.checkCollision(newX, getY() + 31);

        if (!collision) {
            setX(newX);
        } else {
            if (env != null
                    && env.checkCollision(getX(), getY() + 32)
                    && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(-12);
            }
        }
    }

    @Override
    public void vaADroite() {
        newX = getX() + getVitesse();
        if (!aVuJoueur) {
            if (newX > maxX) newX = maxX;
        }
        boolean collision = env.checkCollision(newX + 31, getY()) || env.checkCollision(newX + 31, getY() + 31);
        if (!collision) {
            setX(newX);
        } else {
            if (env != null
                    && env.checkCollision(getX(), getY() + 32)
                    && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(-12);
            }
        }
    }

    public void seDeplacerRandom() {
        if (directionCourante == 'g') {
            vaAGauche();
        } else if (directionCourante == 'd') {
            vaADroite();
        }
    }

    public void seDeplacer() {
        Character joueur = env.getHero();
        // Vérifie si le joueur est dans la zone de détection
        if (!aVuJoueur && Math.abs(getX() - joueur.getX()) < 256) {
            aVuJoueur = true;
        }

        if (aVuJoueur) {
            // Suit le joueur
            if (joueur.getX() < getX()) {
                vaAGauche();
            } else if (joueur.getX() > getX()) {
                vaADroite();
            }
        } else {
            // Déplacement normal
            seDeplacerRandom();
        }
    }
}