package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Weapon;
import universite_paris8.iut.rgarry.ashforged.model.ia.MovementStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ennemis extends NonePlayer {
    private int stats_multiplier;
    private ItemInterface item;
    private final int initialX;
    private int force;
    private final Random random = new Random();
    private final int JUMP_STRENGHT = -12;

    private ItemInterface holdingItem;

    private int minX;
    private int maxX;

    private char directionCourante = 'i';

    // Strategy de déplacement
    private MovementStrategy movementStrategy;

    public Ennemis(String name, int x, int y,int speed, int health, int force, char direction,  ItemInterface item, double velocityY) {
        super(name, x, y, speed, health, direction, velocityY);
        this.item = item;
        this.force = force;
        this.initialX = x;
        this.setHoldingItem(item);
    }

    // ====== Déplacement bas niveau (utilisés par les stratégies) ======
    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        if (r == 0) directionCourante = 'g';
        else if (r == 1) directionCourante = 'd';
        else directionCourante = 'i';
    }

    public void seDeplacerRandom() {
        if (directionCourante == 'g') {
            vaAGauche();
        } else if (directionCourante == 'd') {
            vaADroite();
        }
    }

    public void vaADroite() {
        int newX = getX() + getSpeed();
        if (!Environment.getInstance().checkCollision(newX + 31, getY()) &&
                !Environment.getInstance().checkCollision(newX + 31, getY() + 31) &&
                Environment.getInstance().getField().isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (Environment.getInstance().checkCollision(getX(), getY() + 32) &&
                    Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGHT);
            }
        }
    }

    public void vaAGauche() {
        int newX = getX() - getSpeed();
        if (!Environment.getInstance().checkCollision(newX, getY()) &&
                !Environment.getInstance().checkCollision(newX, getY() + 31) &&
                Environment.getInstance().getField().isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (Environment.getInstance().checkCollision(getX(), getY() + 32) &&
                    Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGHT);
            }
        }
    }

    @Override
    public void seDeplacer() {
        if (movementStrategy != null) {
            movementStrategy.move(this, Environment.getInstance());
        }
    }

    public void onDeath() {
        Random rand = new Random();

        if (getHoldingItem() != null && rand.nextInt(100) < 5) {
            // drop arme (à connecter à l'inventaire du héros)
        }

        List<ItemInterface> resources = Arrays.asList(
                Usuable.iron,
                Usuable.canon_powder,
                Usuable.perlimpinpin_powder,
                Usuable.feather,
                Usuable.ball,
                Usuable.string,
                Usuable.coal,
                Usuable.enchanted_mineral,
                Usuable.golden_piece
        );

        int numDrops = 1 + rand.nextInt(resources.size());
        for (int i = 0; i < numDrops; i++) {
            ItemInterface resource = resources.get(rand.nextInt(resources.size()));
            // drop ressource (à connecter à l'inventaire du héros)
        }
    }

    public void attack() {
        System.out.println(this.getName() + " Health:" + this.health());
        if (getHoldingItem() != null && getHoldingItem() instanceof Weapon) {
            for (Entity entity : Environment.getInstance().getEntities()) {
                if (!(entity instanceof Ennemis)) {
                    int entityX = entity.getX() / 64;
                    int entityY = entity.getY() / 64;
                    int mobX = getX() / 64;
                    int mobY = getY() / 64;

                    if (Math.abs(entityX - mobX) < 2 && Math.abs(entityY - mobY) < 2) {
                        int damage = (getForce() > 1)
                                ? (int) (getForce() * 0.5 + ((double) getHoldingItem().getDamage() / 2))
                                : getHoldingItem().getDamage() / 2;
                        this.health().setHealthProperty(health().getHealth() - damage);
                    }
                }
            }
        }
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public int getForce(){return force;}

    public ItemInterface getHoldingItem() { return holdingItem; }
    public void setHoldingItem(ItemInterface holdingItem) { this.holdingItem = holdingItem; }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
}
