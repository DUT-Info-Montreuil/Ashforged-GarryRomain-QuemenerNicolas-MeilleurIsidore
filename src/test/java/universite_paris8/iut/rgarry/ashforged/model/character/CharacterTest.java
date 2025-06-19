package universite_paris8.iut.rgarry.ashforged.model.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;



public class CharacterTest {
    private Character character;
    private Environment environment;
    private Field field;


    // Test de l'attaque sur une entité à proximité
    @Test
    public void testAttackEntityInRange() {
        // Ajouter un mob à proximité
        Mobs mob = new Mobs("TestMob", 1, new int[]{10, 1, 5, 1}, 1, ItemStock.Weapon.stick, character.getX() + 64, character.getY(), environment);
        environment.getMobs().add(mob);
        character.setHoldingItem(ItemStock.Weapon.stick);

        int initialMobHealth = mob.getHealth();
        int expectedDamage = character.getHoldingItem().getDamage() / 2; // stats[1] = 1, donc damage = item.getDamage()/2

        character.attack();

        assertEquals(initialMobHealth - expectedDamage, mob.getHealth(), "Le mob devrait subir des dégâts.");
    }

    // Test de l'attaque sans entité à proximité
    @Test
    public void testAttackNoEntityInRange() {
        // Ajouter un mob hors de portée
        Mobs mob = new Mobs("TestMob", 1, new int[]{10, 1, 5, 1}, 1, ItemStock.Weapon.stick, character.getX() + 200, character.getY() + 200, environment);
        environment.getMobs().add(mob);
        character.setHoldingItem(ItemStock.Weapon.stick);

        int initialMobHealth = mob.getHealth();

        character.attack();

        assertEquals(initialMobHealth, mob.getHealth(), "Le mob ne devrait pas subir de dégâts s'il est hors de portée.");
    }

    // Test de l'ajout d'un item à l'inventaire
    @Test
    public void testAddToInventory() {
        ItemInterface item = ItemStock.Usuable.golden_piece;
        character.addToInventory(item);

        assertTrue(character.getInventory().containsKey(item), "L'item devrait être ajouté à l'inventaire.");
        assertEquals(1, character.getInventory().get(item), "La quantité de l'item devrait être 1.");
    }

    // Test de l'expérience et montée de niveau
    @Test
    public void testGainExpAndLevelUp() {
        int initialLevel = character.getLevel();
        int expToNextLevel = character.getExpToNextLevel();

        character.gainExp(expToNextLevel);

        assertEquals(initialLevel + 1, character.getLevel(), "Le personnage devrait monter d'un niveau.");
        assertEquals(0, character.getExp(), "L'expérience devrait être réinitialisée après montée de niveau.");
        assertEquals(expToNextLevel + 5, character.getExpToNextLevel(), "L'expérience nécessaire pour le prochain niveau devrait augmenter.");
    }


}