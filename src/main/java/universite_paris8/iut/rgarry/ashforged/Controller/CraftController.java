package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.util.HashMap;

public class CraftController {
    private HashMap<ItemInterface, Integer> inventory;
    
    @FXML
    private Label affichageResultatLabel;

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

    private Character character;

    public void setCharacter(Character character) {
        this.character = character;
        updateInventoryLabels();
    }

    private void updateInventoryLabels() {
        if (character == null) {
            return;
        }
        inventory = character.getInventory();

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

    public void craftBow(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.feather, 0) >= 1) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.feather);
            character.addToInventory(ItemStock.Weapon.bow);
            affichageResultatLabel.setText("Arc fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un arc");
        }
        updateInventoryLabels();
    }

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

    public void craftWoodenKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 4) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.addToInventory(ItemStock.Weapon.wooden_knife);
            affichageResultatLabel.setText("Couteau en bois fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en bois");
        }
        updateInventoryLabels();
    }

    public void craftStoneKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.ground, 0) >= 2) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.removeFromInventory(ItemStock.Usuable.ground);
            character.addToInventory(ItemStock.Weapon.stone_knife);
            affichageResultatLabel.setText("Couteau en pierre fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en pierre");
        }
        updateInventoryLabels();
    }

    public void craftIronKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.iron, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.removeFromInventory(ItemStock.Usuable.iron);
            character.addToInventory(ItemStock.Weapon.iron_knife);
            affichageResultatLabel.setText("Couteau en fer fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en fer");
        }
        updateInventoryLabels();
    }

    public void craftAluminiumKnife(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.alluminium, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.addToInventory(ItemStock.Weapon.aluminium_knife);
            affichageResultatLabel.setText("Couteau en aluminium fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un couteau en aluminium");
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

    public void craftAluminiumSword(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.alluminium, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.addToInventory(ItemStock.Weapon.aluminium_sword);
            affichageResultatLabel.setText("Épée en aluminium fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une épée en aluminium");
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

    public void craftAluminiumSabre(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.alluminium, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.addToInventory(ItemStock.Weapon.aluminium_sabre);
            affichageResultatLabel.setText("Sabre en aluminium fabriqué avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer un sabre en aluminium");
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

    public void craftAluminiumAxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.alluminium, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.addToInventory(ItemStock.Weapon.aluminium_axe);
            affichageResultatLabel.setText("Hache en aluminium fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une hache en aluminium");
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

    public void craftAluminiumPickaxe(ActionEvent actionEvent) {
        if (inventory.getOrDefault(ItemStock.Usuable.wood, 0) >= 2 &&
                inventory.getOrDefault(ItemStock.Usuable.alluminium, 0) >= 3) {
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.wood);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.removeFromInventory(ItemStock.Usuable.alluminium);
            character.addToInventory(ItemStock.Weapon.aluminium_pickaxe);
            affichageResultatLabel.setText("Pioche en aluminium fabriquée avec succès!");
        } else {
            affichageResultatLabel.setText("Ressources insuffisantes pour créer une pioche en aluminium");
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