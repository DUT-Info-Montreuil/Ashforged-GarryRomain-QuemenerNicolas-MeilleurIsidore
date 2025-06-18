package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

public class Character extends Entity {

    public Character(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        setHoldingItem(null);
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

    public void attack() {
        for (Entity entity : env.getEntities()) {
            int dx = Math.abs(entity.getX() / 64 - this.getX() / 64);
            int dy = Math.abs(entity.getY() / 64 - this.getY() / 64);
            if (dx < 2 && dy < 2) {
                int damage;
                if (stats[1] > 1) damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() /2));
                else damage = getHoldingItem().getDamage()/2;
                entity.setHealth(entity.getHealth() - damage);
            }
        }
    }
}