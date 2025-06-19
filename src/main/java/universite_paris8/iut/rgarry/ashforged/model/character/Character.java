package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
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
            if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31) && isWithinMap(newX, getY())) {
                setX(newX);
                test = false;
            } else {
                if (!test) {
                    // Place the character just before the wall (1 pixel away)
                    int blockX = ((newX + 31) / 64) * 64;
                    setX(blockX - 31 - 1);
                    test = true;
                }
            }
        } else if (direction == 'g') {
            newX -= getVitesse();
            if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31) && isWithinMap(newX, getY())) {
                setX(newX);
                test = false;
            } else {
                if (!test) {
                    // Place the character just after the wall (1 pixel away)
                    int blockX = (newX / 64) * 64;
                    setX(blockX + 64 + 1);
                    test = true;
                }
            }
        }
    }

    @Override
    public void attack() {
        if (getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if(entity instanceof Mobs) {
                    int dx = Math.abs(entity.getX() / 64 - this.getX() / 64);
                    int dy = Math.abs(entity.getY() / 64 - this.getY() / 64);
                    if (dx < 2 && dy < 2) {
                        System.out.println("HUSSSSS !");
                        int damage;
                        if (stats[1] > 1) damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        else damage = getHoldingItem().getDamage() / 2;
                        if(entity.getHealth() - damage <= 0) {
                            entity.setHealth(0);
                            System.out.println("Vous avez tué " + entity.getName() + " !");
                            System.out.println(entity.getLevel());
                            this.gainExp(entity.getLevel());
                        } else {
                            entity.setHealth(entity.getHealth() - damage);
                            System.out.println("Vous avez infligé " + damage + " points de dégâts à " + entity.getName() + " !");
                        }
                    }
                }
            }
        }
    }

    private boolean isWithinMapX(int x) {
        return x >= 0 && x + 31 < env.getField().getWidth(); // 31: entity width in pixels
    }

    private boolean isWithinMapY(int y) {
        return y >= 0 && y + 31 < env.getField().getHeight(); // 31: entity height in pixels
    }
}
