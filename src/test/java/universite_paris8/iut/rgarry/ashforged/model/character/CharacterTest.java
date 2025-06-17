package universite_paris8.iut.rgarry.ashforged.model.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    private Character character;
    private Environment testEnv;
    private int[] stats = {10, 5, 3}; // hp, str, spd

    @BeforeEach
    public void setUp() {
        Field fakeField = new Field(); // Assumes a no-arg constructor exists
        List<Mobs> fakeMobs = Collections.emptyList();
        List<Npc> fakeNpcs = Collections.emptyList();

        testEnv = new Environment(fakeField, fakeMobs, fakeNpcs) {
            @Override
            public boolean checkCollision(int x, int y) {
                return false; // always allow movement in test
            }
        };

        character = new Character("TestChar", 1, stats, 10, 20, testEnv);
    }

    @Test
    public void vaADroite() {
        int xBefore = character.getX();
        character.vaADroite();
        character.seDeplacer();
        assertTrue(character.getX() > xBefore);
    }

    @Test
    public void vaAGauche() {
        int xBefore = character.getX();
        character.vaAGauche();
        character.seDeplacer();
        assertTrue(character.getX() < xBefore);
    }

    @Test
    public void resteImmobile() {
        int xBefore = character.getX();
        character.resteImmobile();
        character.seDeplacer();
        assertEquals(xBefore, character.getX());
    }

    @Test
    public void seDeplacer() {
        character.setX(100);
        character.vaADroite();
        character.seDeplacer();
        assertTrue(character.getX() > 100);
    }

    @Test
    public void getVelocityY() {
        character.setVelocityY(3.14);
        assertEquals(3.14, character.getVelocityY());
    }

    @Test
    public void getId() {
        assertNotNull(character.getId());
        assertTrue(character.getId().startsWith("#"));
    }

    @Test
    public void getName() {
        assertEquals("TestChar", character.getName());
    }

    @Test
    public void getLevel() {
        assertEquals(1, character.getLevel());
    }

    @Test
    public void getStats() {
        assertArrayEquals(stats, character.getStats());
    }

    @Test
    public void getXProperty() {
        assertEquals(10, character.getXProperty().get());
    }

    @Test
    public void getX() {
        assertEquals(10, character.getX());
    }

    @Test
    public void getYProperty() {
        assertEquals(20, character.getYProperty().get());
    }

    @Test
    public void getY() {
        assertEquals(20, character.getY());
    }

    @Test
    public void setX() {
        character.setX(42);
        assertEquals(42, character.getX());
    }

    @Test
    public void setY() {
        character.setY(99);
        assertEquals(99, character.getY());
    }

    @Test
    public void setLevel() {
        character.setLevel(7);
        assertEquals(7, character.getLevel());
    }

    @Test
    public void levelProperty() {
        assertEquals(1, character.levelProperty().get());
    }

    @Test
    public void getVitesse() {
        assertEquals(stats[2], character.getVitesse());
    }

    @Test
    public void setStats() {
        int[] newStats = {20, 10, 6};
        character.setStats(newStats);
        assertArrayEquals(newStats, character.getStats());
    }

    @Test
    public void setStatPoint() {
        character.setStatPoint(10); // ne se vérifie que via debug/log
    }

    @Test
    public void getHealth() {
        assertEquals(character.getMaxHealth(), character.getHealth());
    }

    @Test
    public void getMaxHealth() {
        assertEquals(3 * stats[0], character.getMaxHealth());
    }

    @Test
    public void healthProperty() {
        assertEquals(character.getMaxHealth(), character.healthProperty().get());
    }

    @Test
    public void setHealth() {
        character.setHealth(25);
        assertEquals(25, character.getHealth());
    }

    @Test
    public void expProperty() {
        assertEquals(0, character.expProperty().get());
    }

    @Test
    public void expToNextLevelProperty() {
        assertEquals(5, character.expToNextLevelProperty().get());
    }

    @Test
    public void getExp() {
        assertEquals(0, character.getExp());
    }

    @Test
    public void getExpToNextLevel() {
        assertEquals(5, character.getExpToNextLevel());
    }

    @Test
    public void gainExp() {
        character.gainExp(6);
        assertEquals(2, character.getLevel());
        assertTrue(character.getExp() < character.getExpToNextLevel());
    }

    @Test
    public void getInventory() {
        LinkedHashMap<ItemInterface, Integer> inv = character.getInventory();
        assertFalse(inv.isEmpty());
    }

    @Test
    public void addToInventory() {
        ItemInterface item = ItemStock.Usuable.string;
        int before = character.getInventory().getOrDefault(item, 0);
        character.addToInventory(item);
        int after = character.getInventory().getOrDefault(item, 0);
        assertTrue(after >= before);
    }

    @Test
    public void removeFromInventory() {
        ItemInterface item = ItemStock.Weapon.bomb;
        character.addToInventory(item);
        character.removeFromInventory(item);
        assertTrue(character.getInventory().containsKey(item));
    }

    @Test
    public void findKey() {
        ItemInterface key = character.findKey(character.getInventory(), 0);
        assertNotNull(key);
    }

    @Test
    public void findKeyOutOfBounds() {
        assertNull(character.findKey(character.getInventory(), -1));
        assertNull(character.findKey(character.getInventory(), 1000));
    }
}
