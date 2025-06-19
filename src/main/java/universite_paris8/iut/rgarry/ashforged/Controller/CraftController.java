package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.util.HashMap;

/**
 * Contrôleur lié à l'interface utilisateur de l'atelier de craft.
 * Permet la fabrication d'armes à partir de matériaux contenus dans l'inventaire du personnage.
 */
public class CraftController {

    // Inventaire du personnage (clé = objet, valeur = quantité)
    private HashMap<ItemInterface, Integer> inventory;

    // Références aux labels de l'interface pour l'affichage des ressources
    @FXML private Label affichageResultatLabel;
    @FXML private Label woodLabel;
    @FXML private Label ironLabel;
    @FXML private Label aluminiumLabel;
    @FXML private Label dirtLabel;
    @FXML private Label poudreACanonLabel;
    @FXML private Label PoudreDePerlimpinpinLabel;
    @FXML private Label FilLabel;
    @FXML private Label PlumeLabel;
    @FXML private Label CharbonLabel;
    @FXML private Label MineraiEnchanteLabel;

    // Personnage qui effectue le craft
    private Character character;

    /**
     * Initialise le personnage et met à jour l'affichage de son inventaire.
     */
    public void setCharacter(Character character) {
        this.character = character;
        updateInventoryLabels();
    }

    /**
     * Met à jour tous les labels d'inventaire avec les quantités actuelles.
     */
    private void updateInventoryLabels() {
        if (character == null) return;
        inventory = character.getInventory();

        dirtLabel.setText(inventory.getOrDefault(ItemStock.Usuable.ground, 0).toString());
        woodLabel.setText(inventory.getOrDefault(ItemStock.Usuable.wood, 0).toString());
        ironLabel.setText(inventory.getOrDefault(ItemStock.Usuable.iron, 0).toString());
        aluminiumLabel.setText(inventory.getOrDefault(ItemStock.Usuable.aluminum, 0).toString());
        poudreACanonLabel.setText(inventory.getOrDefault(ItemStock.Usuable.cannon_powder, 0).toString());
        PoudreDePerlimpinpinLabel.setText(inventory.getOrDefault(ItemStock.Usuable.perlimpinpin_powder, 0).toString());
        FilLabel.setText(inventory.getOrDefault(ItemStock.Usuable.string, 0).toString());
        PlumeLabel.setText(inventory.getOrDefault(ItemStock.Usuable.feather, 0).toString());
        CharbonLabel.setText(inventory.getOrDefault(ItemStock.Usuable.coal, 0).toString());
        MineraiEnchanteLabel.setText(inventory.getOrDefault(ItemStock.Usuable.enchanted_mineral, 0).toString());
    }

    // === MÉTHODES DE CRAFT (arme = dépend de certaines ressources) ===

    /**
     * Arc : 2 bois + 1 plume + 1 fil
     */
    public void craftBow(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.feather, 0) >= 1 &&
                inventory.getOrDefault(ItemStock.Usuable.string, 0) >= 1) {

            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.string);
            character.addToInventory(ItemStock.Weapon.bow);
            affichageResultatLabel.setText("Arc fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un arc");
        }
        updateInventoryLabels();
    }

    /**
     * Bâton : 2 bois
     */
    public void craftStick(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.stick);
            affichageResultatLabel.setText("Bâton fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un bâton");
        }
        updateInventoryLabels();
    }

    /**
     * Couteau en bois : 4 bois
     */
    public void craftWoodenKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            for (int i = 0; i < 4; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.wooden_knife);
            affichageResultatLabel.setText("Couteau en bois fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en bois");
        }
        updateInventoryLabels();
    }

    /**
     * Couteau en pierre : 2 bois + 2 terre
     */
    public void craftStoneKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.ground, 0) >= 2) {

            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.ground);
            character.addToInventory(ItemStock.Weapon.stone_knife);
            affichageResultatLabel.setText("Couteau en pierre fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en pierre");
        }
        updateInventoryLabels();
    }

    /**
     * Couteau en fer : 2 bois + 3 fer
     */
    public void craftIronKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {

            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 3; i++) character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_knife);
            affichageResultatLabel.setText("Couteau en fer fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en fer");
        }
        updateInventoryLabels();
    }

    // ==== ÉPÉES, SABRES, HACHES et PIOCHES ====
    // Même logique pour chaque type d’arme : vérifier les matériaux, les retirer, et ajouter l’objet fabriqué.

    public void craftWoodenSword(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            for (int i = 0; i < 4; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.wooden_sword);
            affichageResultatLabel.setText("Épée en bois fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une épée en bois");
        }
        updateInventoryLabels();
    }

    public void craftStoneSword(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.ground, 0) >= 2) {
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.ground);
            character.addToInventory(ItemStock.Weapon.stone_sword);
            affichageResultatLabel.setText("Épée en pierre fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une épée en pierre");
        }
        updateInventoryLabels();
    }

    public void craftIronSword(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 3; i++) character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_sword);
            affichageResultatLabel.setText("Épée en fer fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une épée en fer");
        }
        updateInventoryLabels();
    }

    // Sabres : même logique
    public void craftWoodenSabre(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            for (int i = 0; i < 4; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.wooden_sabre);
            affichageResultatLabel.setText("Sabre en bois fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un sabre en bois");
        }
        updateInventoryLabels();
    }

    public void craftStoneSabre(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.ground, 0) >= 2) {
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.ground);
            character.addToInventory(ItemStock.Weapon.stone_sabre);
            affichageResultatLabel.setText("Sabre en pierre fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un sabre en pierre");
        }
        updateInventoryLabels();
    }

    public void craftIronSabre(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 3; i++) character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_sabre);
            affichageResultatLabel.setText("Sabre en fer fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un sabre en fer");
        }
        updateInventoryLabels();
    }

    // Haches, Pioches et autres outils suivent le même schéma
    public void craftWoodenAxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            for (int i = 0; i < 4; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.wooden_axe);
            affichageResultatLabel.setText("Hache en bois fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une hache en bois");
        }
        updateInventoryLabels();
    }

    public void craftStoneAxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.ground, 0) >= 2) {
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.ground);
            character.addToInventory(ItemStock.Weapon.stone_axe);
            affichageResultatLabel.setText("Hache en pierre fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une hache en pierre");
        }
        updateInventoryLabels();
    }

    public void craftIronAxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 3; i++) character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_axe);
            affichageResultatLabel.setText("Hache en fer fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une hache en fer");
        }
        updateInventoryLabels();
    }

    // ==== CRAFTS AVANCÉS ====

    public void craftFirearm(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 4 &&
                inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 1 &&
                inventory.getOrDefault(ItemStock.Usuable.cannon_powder, 0) >= 2) {
            for (int i = 0; i < 4; i++) character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 2; i++) character.removeFromInventory(ItemStock.Usuable.cannon_powder);
            character.addToInventory(ItemStock.Weapon.firearm);
            affichageResultatLabel.setText("Arme à feu fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une arme à feu");
        }
        updateInventoryLabels();
    }

    public void craftBomb(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.cannon_powder, 0) >= 3 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 1) {
            for (int i = 0; i < 3; i++) character.removeFromInventory(ItemStock.Usuable.cannon_powder);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.bomb);
            affichageResultatLabel.setText("Bombe fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une bombe");
        }
        updateInventoryLabels();
    }

    public void craftEnma(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.enchanted_mineral, 0) >= 5) {
            for (int i = 0; i < 5; i++) character.removeFromInventory(ItemStock.Usuable.enchanted_mineral);
            character.addToInventory(ItemStock.Weapon.enma);
            affichageResultatLabel.setText("Enma fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer Enma");
        }
        updateInventoryLabels();
    }
}
