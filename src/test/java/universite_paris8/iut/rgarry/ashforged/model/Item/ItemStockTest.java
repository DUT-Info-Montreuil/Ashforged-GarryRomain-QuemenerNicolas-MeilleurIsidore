package universite_paris8.iut.rgarry.ashforged.model.Item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStockTest {

    @Test
    @DisplayName("Test des getters de Weapon")
    public void testWeaponMethods() {
        for (Usuable weapon : Usuable.values()) {
            assertNotNull(weapon.getId(), "getId() ne doit pas retourner null");
            assertNotNull(weapon.getName(), "getName() ne doit pas retourner null");
            assertTrue(weapon.getWeight() > 0, "getWeight() doit être supérieur à 0");
            assertNotNull(weapon.getImagePath(), "getImagePath() ne doit pas retourner null");
        }
    }

    @Test
    @DisplayName("Test des getters de Usuable")
    public void testUsuableMethods() {
        for (Consomable item : Consomable.values()) {
            assertNotNull(item.getId(), "getId() ne doit pas retourner null");
            assertNotNull(item.getName(), "getName() ne doit pas retourner null");
            assertTrue(item.getWeight() >= 0, "getWeight() doit être >= 0");
            assertNotNull(item.getImagePath(), "getImagePath() ne doit pas retourner null");
        }
    }

    @Test
    @DisplayName("Vérifie que les chemins d'image sont bien définis")
    public void testWeaponImagePathsExist() {
        for (Usuable weapon : Usuable.values()) {
            assertNotNull(weapon.getImagePath(), "Le chemin de l'image ne doit pas être null");
            assertFalse(weapon.getImagePath().isEmpty(), "Le chemin de l'image ne doit pas être vide");
        }
    }
}
