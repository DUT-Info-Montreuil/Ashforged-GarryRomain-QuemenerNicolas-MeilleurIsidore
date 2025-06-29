package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.BFS;
import universite_paris8.iut.rgarry.ashforged.model.Position;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Mobs extends Character {
    private int stats_multiplier;
    private ItemInterface item;
    private final int initialX;
    private final Random random = new Random();
    private final int JUMP_STRENGHT = -12;
    private boolean aVuJoueur = false;

    // Bornes de déplacement en tuiles (non initialisées dans le constructeur ici)
    private int minX;
    private int maxX;

    private char directionCourante = 'i';

    private List<Position> currentPath = null;
    private int pathIndex = 1;

    private int lastJoueurCaseX = -1;
    private int lastJoueurCaseY = -1;

    public Mobs(String name, int level, int[] stats, int stats_multiplier, ItemInterface item, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.stats_multiplier = stats_multiplier;
        this.item = item;
        this.initialX = x;
        this.setHoldingItem(item);
    }

    /** Choisit une direction aléatoire */
    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        if (r == 0) directionCourante = 'g';
        else if (r == 1) directionCourante = 'd';
        else directionCourante = 'i';
    }

    /** Se déplace selon direction aléatoire */
    public void seDeplacerRandom() {
        if (directionCourante == 'g') {
            vaAGaucheR();
        } else if (directionCourante == 'd') {
            vaADroiteR();
        }
    }

    @Override
    public void vaAGauche() {
        int newX = getX() - getVitesse();
        if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        }
    }

    @Override
    public void vaADroite() {
        int newX = getX() + getVitesse();
        if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        }
    }

    /** Version avec saut si collision au sol */
    public void vaADroiteR() {
        int newX = getX() + getVitesse();
        if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGHT);
            }
        }
    }

    /** Version avec saut si collision au sol */
    public void vaAGaucheR() {
        int newX = getX() - getVitesse();
        if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGHT);
            }
        }
    }

    @Override
    public void seDeplacer() {
        // Position du joueur
        Character hero = env.getHero();
        int mobX = getX() / 64;
        int mobY = getY() / 64;
        int heroX = hero.getX() / 64;
        int heroY = hero.getY() / 64;

        // Recherche de chemin BFS
        BFS bfs = new BFS(env.getField());
        Position start = new Position(mobX, mobY);
        Position goal = new Position(heroX, heroY);

        List<Position> path = bfs.findPath(start, goal);

        if (Math.abs(heroX - mobX) <= 5) {
            aVuJoueur = true;
        }

        if (aVuJoueur) {
            if (path.size() > 1) {
                Position next = path.get(1);

                if (next.x < mobX) vaAGauche();
                else if (next.x > mobX) vaADroite();

                // Saut si case au-dessus
                if (next.y < mobY) {
                    if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                        setVelocityY(JUMP_STRENGHT);
                    }
                }
            }
        } else {
            seDeplacerRandom();
        }
    }

    public void onDeath() {
        Random rand = new Random();

        // 5% chance to drop weapon
        if (getHoldingItem() != null && rand.nextInt(100) < 5) {
            env.getHero().addToInventory(getHoldingItem());
        }

        // List of possible resources
        List<ItemInterface> resources = Arrays.asList(
                ItemStock.Usuable.iron,
                ItemStock.Usuable.canon_powder,
                ItemStock.Usuable.perlimpinpin_powder,
                ItemStock.Usuable.feather,
                ItemStock.Usuable.ball,
                ItemStock.Usuable.string,
                ItemStock.Usuable.coal,
                ItemStock.Usuable.enchanted_mineral,
                ItemStock.Usuable.golden_piece
        );

        // Randomly decide how many resources to drop (at least 1)
        int numDrops = 1 + rand.nextInt(resources.size());

        for (int i = 0; i < numDrops; i++) {
            ItemInterface resource = resources.get(rand.nextInt(resources.size()));
            env.getHero().addToInventory(resource);
        }
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " Health:" + this.getHealth());
        if (getHoldingItem() != null && getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if (!(entity instanceof Mobs)) {
                    int entityX = entity.getX() / 64;
                    int entityY = entity.getY() / 64;
                    int mobX = getX() / 64;
                    int mobY = getY() / 64;

                    if (Math.abs(entityX - mobX) < 2 && Math.abs(entityY - mobY) < 2) {
                        int damage;
                        if (stats[1] > 1)
                            damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        else
                            damage = getHoldingItem().getDamage() / 2;
                        entity.setHealth(entity.getHealth() - damage);
                    }
                }
            }
        }
    }

    public void action() {
        this.applyGravity(env);
        this.seDeplacer();
    }
}
