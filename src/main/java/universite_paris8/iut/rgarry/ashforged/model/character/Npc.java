// src/main/java/universite_paris8/iut/rgarry/ashforged/model/character/Npc.java
package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import java.util.Random;

public class Npc extends Entity {
    private final int initialX;
    private final Random random = new Random();
    int minX; // Position minimale pour l'axe
    int newX;
    int maxX;

    public Npc(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.initialX = x;
        minX = initialX - 640;
        maxX = initialX + 640;
    }

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
        if (newX < minX) newX = minX;
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
        if (newX > maxX) newX = maxX;
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

    public void seDeplacer() {
        if (directionCourante == 'g') {
            vaAGauche();
        } else if (directionCourante == 'd') {
            vaADroite();
        }
    }
}