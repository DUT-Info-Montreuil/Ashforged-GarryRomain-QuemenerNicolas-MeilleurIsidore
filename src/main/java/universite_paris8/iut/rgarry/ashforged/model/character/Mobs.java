package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.BFS;
import universite_paris8.iut.rgarry.ashforged.model.Position;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.List;
import java.util.Random;

public class Mobs extends Character {
    private int stats_multiplier;
    private ItemInterface item;
    private final int initialX;
    private final Random random = new Random();
    private final int JUMP_STRENGHT = -12;
    private boolean aVuJoueur = false;

    // Ajout des bornes de déplacement en tuiles
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

    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        if (r == 0) directionCourante = 'g';
        else if (r == 1) directionCourante = 'd';
        else directionCourante = 'i';
    }

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
        if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31)) {
            setX(newX);
        }
    }

    @Override
    public void vaADroite() {
        int newX = getX() + getVitesse();
        if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31)) {
            setX(newX);
        }
    }

    public void vaADroiteR() {
        int newX = getX() + getVitesse();
        // Vérifie la collision à droite
        if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31)) {
            setX(newX);
        } else {
            // Collision détectée, saute
            if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(-12); // Valeur à ajuster selon la hauteur de saut désirée
            }
        }
    }

    public void vaAGaucheR() {
        int newX = getX() - getVitesse();
        // Vérifie la collision à gauche
        if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31)) {
            setX(newX);
        } else {
            // Collision détectée, saute
            if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(-12); // Même valeur que pour droite
            }
        }
    }

    @Override
    public void seDeplacer() {
        // Récupère la position du joueur
        Character hero = env.getHero();
        int mobX = getX() / 64;
        int mobY = getY() / 64;
        int heroX = hero.getX() / 64;
        int heroY = hero.getY() / 64;

        // Utilise BFS pour trouver le chemin
        BFS bfs = new BFS(env.getField());
        Position start = new Position(mobX, mobY);
        Position goal = new Position(heroX, heroY);

        List<Position> path = bfs.findPath(start, goal);

        if (Math.abs(heroX - mobX) <= 5) {
            aVuJoueur = true;
        }

        // Si un chemin existe et qu'il y a un prochain mouvement
        if (aVuJoueur) {
            if (path.size() > 1) {
                Position next = path.get(1);

                if (next.x < mobX) {
                    vaAGauche();
                } else if (next.x > mobX) {
                    vaADroite();
                }

                // Sauter si la prochaine case est au-dessus
                if (next.y < mobY) {
                    if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                        setVelocityY(JUMP_STRENGHT);
                    }
                }
            }
        } if(!aVuJoueur) {
            seDeplacerRandom();
        }
    }

    @Override
    public void attack() {
        if (getHoldingItem() != null && this.getHoldingItem() instanceof ItemStock.Weapon) {
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