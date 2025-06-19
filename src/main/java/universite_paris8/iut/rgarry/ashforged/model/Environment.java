package universite_paris8.iut.rgarry.ashforged.model;

import javafx.scene.Node;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;
import universite_paris8.iut.rgarry.ashforged.view.MobView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

    private Field field;
    private Character hero;
    private List<Mobs> mobs = new ArrayList<>();          // Liste des mobs
    private List<Npc> npcs = new ArrayList<>();          // Liste des NPCs
    private List<Entity> entities = new ArrayList<>();   // Liste de toutes les entités (hero, mobs, npcs)
    private int maxMobs = 5; // Nombre maximum de mobs à générer

    public enum Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    public Environment(Field field) {
        this.field = field;
        this.mobs = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.hero = new Character("Hero", 1, new int[]{10, 1, 5, 1}, 250, 300, this);

        this.mobs.add(new Mobs("Mongolfière", 15, new int[]{10, 1, 3, 1}, 5, ItemStock.Weapon.wooden_sabre, 1664, 300, this));
       mobs.add(new Mobs("Kozuki", 30, new int[]{1, 1, 3, 1}, 5, ItemStock.Weapon.enma, 600, 250,  this));

        this.npcs.add(new Npc("Paolo", 15,  new int[]{1, 1, 10, 1}, 2500,400, this));
        this.npcs.add(new Npc("Branda",  15,  new int[]{1, 1, 10, 1}, 2500,400, this));
        this.npcs.add(new Npc("Terry", 15,  new int[]{1, 1, 10, 1}, 2500,400, this));
            this.npcs.add(new Npc("Salome", 15,  new int[]{1, 1, 10, 1}, 2500,400, this));

        this.entities.addAll(mobs);
        this.entities.addAll(npcs);
        this.entities.add(hero);
    }

    public Field getField() {
        return field;
    }

    public Character getHero() {
        return hero;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mobs> getMobs() {
        return mobs;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Mobs) {
            mobs.add((Mobs) entity);
        } else if (entity instanceof Npc) {
            npcs.add((Npc) entity);
        }
    }

    public void removeEntity(Entity entity) {
        if (entity instanceof Mobs) {
            mobs.remove(entity);
        } else if (entity instanceof Npc) {
            npcs.remove(entity);
        }
        entities.remove(entity);
    }

    public boolean checkCollision(int x, int y) {
        return field.checkCollision(x, y);
    }

    public void generateRandomMob() {
        Random rand = new Random();
        String[] mobNames = {"Mongolfière", "Soldat", "Zombie", "Bandit", "Boss", "Kozuki"};
        ItemStock.Weapon[] mobWeapons = {
                ItemStock.Weapon.wooden_sabre,
                ItemStock.Weapon.stone_sword,
                ItemStock.Weapon.stick,
                ItemStock.Weapon.firearm,
                ItemStock.Weapon.iron_sabre,
                ItemStock.Weapon.enma
        };

        int heroLevel = hero.getLevel();
        int[] heroStats = hero.getStats();
        int heroStatSum = 0;
        for (int s : heroStats) heroStatSum += s;

        for (int i = 0; i + this.mobs.size() < maxMobs; i++) {
            int idx = rand.nextInt(mobNames.length);
            int mobLevel = Math.max(1, rand.nextInt(heroLevel + 5 - Math.max(1, heroLevel - 5) + 1) + Math.max(1, heroLevel - 5));
            int statPointPool = heroStatSum * Math.max(1, Math.abs(1 + (mobLevel - heroLevel)/10));

            // Randomly distribute statPointPool into 4 stats
            int[] mobStats = new int[4];
            int pointsLeft = statPointPool;
            for (int s = 0; s < 3; s++) {
                mobStats[s] = rand.nextInt(pointsLeft + 1);
                pointsLeft -= mobStats[s];
            }
            mobStats[3] = pointsLeft;

            int x, y, attempts = 0;
            do {
                x = rand.nextInt(this.field.getWidth() * 64);
                y = rand.nextInt(this.field.getHeight() * 64);
                attempts++;
                System.out.println("Attempt failed for mob: " + mobNames[idx] + " at (" + x + ", " + y + ")");
            } while (checkCollision(x, y));

            if (!checkCollision(x, y)) {
                System.out.println("Generating mob: " + mobNames[idx] + " (lvl " + mobLevel + ") at (" + x + ", " + y + ")");
                Mobs mob = new Mobs(
                        mobNames[idx],
                        mobLevel,
                        mobStats,
                        5, // multiplier or other param as before
                        mobWeapons[idx],
                        x,
                        y,
                        this
                );
                this.mobs.add(mob);
                this.addEntity(mob);
            }
        }
    }
}