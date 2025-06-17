package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.util.HashMap;

public class CraftController {

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

    public void setCharacter(Character character){
        this.character=character;
        updateInventoryLabels();
    }


    private void updateInventoryLabels() {
        if (character == null){

        }
        else {

        HashMap<ItemInterface, Integer> inventory = character.getInventory();

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
    }

    public void craftSword(ActionEvent actionEvent) {

    }

    public void craftShield(ActionEvent actionEvent) {
    }
}
