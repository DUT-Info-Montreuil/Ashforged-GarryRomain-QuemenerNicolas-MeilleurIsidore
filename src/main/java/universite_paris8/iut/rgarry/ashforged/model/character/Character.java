package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;

public class Character extends Entity {
    public Character(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
    }

    public void seDeplacer() {
        int newX = getX();
        if (direction == 'd') {
            newX += getVitesse();
            if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31)) {
                setX(newX);
                test = false;
            } else {
                if (!test) {
                    setX((newX / 64) * 64 + 31);
                    test = true;
                }
            }
        } else if (direction == 'g') {
            newX -= getVitesse();
            if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31)) {
                setX(newX);
                test = false;
            } else {
                if (!test) {
                    setX(((newX / 64)+1) * 64+1);
                    test = true;
                }
            }
        }
    }
}