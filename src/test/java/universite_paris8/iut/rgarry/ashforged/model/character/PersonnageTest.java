package universite_paris8.iut.rgarry.ashforged.model.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import static org.junit.jupiter.api.Assertions.*;

public class PersonnageTest {
    private Personnage perso;

    @BeforeEach
    public void setUp() {
        int[] stats = {10, 5, 2}; // hp, str, spd
        perso = new Personnage("TestHero", 1, stats, 0, 0);
    }

    @Test
    public void testInitialProperties() {
        assertEquals("TestHero", perso.getName());
        assertEquals(1, perso.getLevel());
        assertEquals(0, perso.getX());
        assertEquals(0, perso.getY());
        assertEquals(2, perso.getVitesse());
    }

    @Test
    public void testDeplacementUp() {
        perso.deplacer('u', 10, 10);
        assertEquals(0, perso.getX());
        assertEquals(0, perso.getY()); // y already 0, should not go below
    }

    @Test
    public void testDeplacementDown() {
        perso.deplacer('d', 10, 10);
        assertEquals(0, perso.getX());
        assertEquals(2, perso.getY()); // vitesse = 2
    }

    @Test
    public void testDeplacementLeft() {
        perso.deplacer('l', 10, 10);
        assertEquals(0, perso.getX()); // x already 0, should not go below
        assertEquals(0, perso.getY());
    }

    @Test
    public void testDeplacementRight() {
        perso.deplacer('r', 10, 10);
        assertEquals(2, perso.getX());
        assertEquals(0, perso.getY());
    }

    @Test
    public void testAddToInventorySuccess() {
        perso.addToInventory(ItemStock.Weapon.wooden_sword);
        // Check if item is added correctly
        // (the method doesn't return or expose inventory, so no assert here unless refactored)
    }

    @Test
    public void testAddToInventoryFailDueToPods() {
        // set max pods very low to simulate failure
        int[] lowStats = {10, 0, 2}; // str = 0 -> maxPods = 0
        Personnage weakPerso = new Personnage("Weakling", 1, lowStats, 0, 0);
        weakPerso.addToInventory(ItemStock.Weapon.wooden_sword);
        // Should print a message about full or too much pods
    }

    @Test
    public void testRemoveFromInventory() {
        perso.addToInventory(ItemStock.Weapon.stick);
        perso.removeFromInventory(ItemStock.Weapon.stick);
        // Again, method only prints. No assert unless inventory is exposed.
    }

    @Test
    public void testSetLevel() {
        perso.setLevel(5);
        assertEquals(5, perso.getLevel());
    }

    @Test
    public void testSetStats() {
        int[] newStats = {15, 8, 3};
        perso.setStats(newStats);
        assertArrayEquals(newStats, perso.getStats());
    }
}
