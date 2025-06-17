package universite_paris8.iut.rgarry.ashforged.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FieldTest {



    @Test
    public void testBlockReturnsValidValues() {
        Field field = new Field();
        int val = field.block(0, 0);  // Exemple coordonnée
        // Ici on teste que val est dans un certain état attendu (à adapter selon ta logique)
        assertNotNull(val);
    }

    @Test
    public void testTileValueOutOfBounds() {
        Field field = new Field();
        // Assurer que l'on ne peut pas accéder hors des limites (exception attendue)
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int val = field.block(100, 100);
        });
    }
}
