package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.util.HashMap;

public class CraftController {
    // Inventory map holding items and their quantities
    private HashMap<ItemInterface, Integer> inventory;

    // Label to display crafting result messages
    @FXML
    private Label affichageResultatLabel;

    // Labels displaying current quantity of resources in the inventory
    @FXML
    private Label woodLabel;

    @FXML
    private Label ironLabel;

    @FXML
    private Label aluminiumLabel;

    @FXML
    private Label dirtLabel;

    @FXML
    private Label poudreACanonLabel;

    @FXML
    private Label PoudreDePerlimpinpinLabel;

    @FXML
    private Label FilLabel;

    @FXML
    private Label PlumeLabel;

    @FXML
    private Label CharbonLabel;

    @FXML
    private Label MineraiEnchanteLabel;

    // The character whose inventory we are managing
    private Character character;

    /**
     * Sets the character instance and updates the inventory labels accordingly.
     * @param character The Character object whose inventory will be displayed and manipulated.
     */
    public void setCharacter(Character character) {
        this.character = character;
        updateInventoryLabels();
    }

    /**
     * Updates all resource labels with the current quantities in the character's inventory.
     * If character is null, no update occurs.
     */
    private void updateInventoryLabels() {
        if (character == null) {
            return;
        }
        // Retrieve current inventory from character
        inventory = character.getInventory();

        // Update each resource label with corresponding item quantity or zero if absent
        dirtLabel.setText(inventory.getOrDefault(ItemStock.Usuable.ground, 0).toString());
        woodLabel.setText(inventory.getOrDefault(ItemStock.Usuable.wood, 0).toString());
        ironLabel.setText(inventory.getOrDefault(ItemStock.Usuable.iron, 0).toString());
        aluminiumLabel.setText(inventory.getOrDefault(ItemStock.Usuable.alluminium, 0).toString());
        poudreACanonLabel.setText(inventory.getOrDefault(ItemStock.Usuable.canon_powder, 0).toString());
        PoudreDePerlimpinpinLabel.setText(inventory.getOrDefault(ItemStock.Usuable.perlimpinpin_powder, 0).toString());
        FilLabel.setText(inventory.getOrDefault(ItemStock.Usuable.string, 0).toString());
        PlumeLabel.setText(inventory.getOrDefault(ItemStock.Usuable.feather, 0).toString());
        CharbonLabel.setText(inventory.getOrDefault(ItemStock.Usuable.coal, 0).toString());
        MineraiEnchanteLabel.setText(inventory.getOrDefault(ItemStock.Usuable.enchanted_mineral, 0).toString());
    }

    /**
     * Handles crafting a bow if sufficient resources are available.
     * Requires at least 2 wood and 1 feather.
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftBow(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.feather, 0) >= 1) {
            // Remove required materials from inventory
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.string);
            // Add crafted bow to inventory
            character.addToInventory(ItemStock.Weapon.bow);
            affichageResultatLabel.setText("Arc fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un arc");
        }
        updateInventoryLabels();
    }

    /**
     * Handles crafting a stick if sufficient wood is available (2 pieces).
     * Updates inventory and displays a success or failure message.
     * @param actionEvent The action event triggered by the UI.
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
     * Handles crafting a wooden knife if sufficient wood is available (4 pieces).
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftWoodenKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            for (int i = 0; i < 4; i++) {
                character.removeFromInventory(ItemStock.Usuable.wood);
            }
            character.addToInventory(ItemStock.Weapon.wooden_knife);
            affichageResultatLabel.setText("Couteau en bois fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en bois");
        }
        updateInventoryLabels();
    }

    /**
     * Handles crafting a stone knife if sufficient wood and stone are available.
     * Requires at least 2 wood and 2 stone.
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftStoneKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.stone, 0) >= 2) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.stone);
            character.removeFromInventory(ItemStock.Usuable.stone);
            character.addToInventory(ItemStock.Weapon.stone_knife);
            affichageResultatLabel.setText("Couteau en pierre fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en pierre");
        }
        updateInventoryLabels();
    }

    /**
     * Handles crafting an iron knife if sufficient wood and iron are available.
     * Requires at least 2 wood and 3 iron.
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftIronKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            for (int i = 0; i < 3; i++) {
                character.removeFromInventory(ItemStock.Usuable.iron);
            }
            character.addToInventory(ItemStock.Weapon.iron_knife);
            affichageResultatLabel.setText("Couteau en fer fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en fer");
        }
        updateInventoryLabels();
    }

    public void craftWoodenSword(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
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
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.removeFromInventory(ItemStock.Usuable.ground);
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
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_sword);
            affichageResultatLabel.setText("Épée en fer fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une épée en fer");
        }
        updateInventoryLabels();
    }

    public void craftWoodenSabre(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
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
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.removeFromInventory(ItemStock.Usuable.ground);
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
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_sabre);
            affichageResultatLabel.setText("Sabre en fer fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un sabre en fer");
        }
        updateInventoryLabels();
    }

    public void craftWoodenAxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
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
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.removeFromInventory(ItemStock.Usuable.ground);
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
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_axe);
            affichageResultatLabel.setText("Hache en fer fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une hache en fer");
        }
        updateInventoryLabels();
    }


    public void craftWoodenPickaxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.wooden_pickaxe);
            affichageResultatLabel.setText("Pioche en bois fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une pioche en bois");
        }
        updateInventoryLabels();
    }

    public void craftStonePickaxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.ground, 0) >= 2) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.addToInventory(ItemStock.Weapon.stone_pickaxe);
            affichageResultatLabel.setText("Pioche en pierre fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une pioche en pierre");
        }
        updateInventoryLabels();
    }

    public void craftIronPickaxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_pickaxe);
            affichageResultatLabel.setText("Pioche en fer fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une pioche en fer");
        }
        updateInventoryLabels();
    }


    public void craftFirearm(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 4 &&
                inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 1 &&
                inventory.getOrDefault(ItemStock.Usuable.canon_powder, 0) >= 2) {
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.canon_powder);
            character.removeFromInventory(ItemStock.Usuable.canon_powder);
            character.addToInventory(ItemStock.Weapon.firearm);
            affichageResultatLabel.setText("Arme à feu fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une arme à feu");
        }
        updateInventoryLabels();
    }

    public void craftBomb(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.canon_powder, 0) >= 3 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 1) {
            character.removeFromInventory(ItemStock.Usuable.canon_powder);
            character.removeFromInventory(ItemStock.Usuable.canon_powder);
            character.removeFromInventory(ItemStock.Usuable.canon_powder);
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
            character.removeFromInventory(ItemStock.Usuable.enchanted_mineral);
            character.removeFromInventory(ItemStock.Usuable.enchanted_mineral);
            character.removeFromInventory(ItemStock.Usuable.enchanted_mineral);
            character.removeFromInventory(ItemStock.Usuable.enchanted_mineral);
            character.removeFromInventory(ItemStock.Usuable.enchanted_mineral);
            character.addToInventory(ItemStock.Weapon.enma);
            affichageResultatLabel.setText("Enma fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer Enma");
        }
        updateInventoryLabels();
    }
}



