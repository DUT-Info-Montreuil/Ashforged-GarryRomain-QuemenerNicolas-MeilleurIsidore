package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import universite_paris8.iut.rgarry.ashforged.model.Craft;
import universite_paris8.iut.rgarry.ashforged.model.Item.Consumables.*;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Item;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.util.HashMap;

public class CraftController {
    // Inventory map holding items and their quantities
    private HashMap<Item, Integer> inventory;

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




    /**
     * Updates all resource labels with the current quantities in the character's inventory.
     * If character is null, no update occurs.
     */
    private void updateInventoryLabels() {
        // Retrieve current inventory from character
        inventory = Character.getInstance().getInventory().getListOfInventory();

        // Update each resource label with corresponding item quantity or zero if absent
        dirtLabel.setText(inventory.getOrDefault(new Ground(), 0).toString());
        woodLabel.setText(inventory.getOrDefault(new Wood(), 0).toString());
        ironLabel.setText(inventory.getOrDefault(new Iron(), 0).toString());
        aluminiumLabel.setText(inventory.getOrDefault(new Alluminium(), 0).toString());
        poudreACanonLabel.setText(inventory.getOrDefault(new CanonPowder(), 0).toString());
        PoudreDePerlimpinpinLabel.setText(inventory.getOrDefault(new PerlimpinpinPowder(), 0).toString());
        FilLabel.setText(inventory.getOrDefault(new String(), 0).toString());
        PlumeLabel.setText(inventory.getOrDefault(new Feather(), 0).toString());
        CharbonLabel.setText(inventory.getOrDefault(new Coal(), 0).toString());
        MineraiEnchanteLabel.setText(inventory.getOrDefault(new EnchantedMineral(), 0).toString());
    }

    /**
     * Handles crafting a bow if sufficient resources are available.
     * Requires at least 2 wood and 1 feather.
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftBow(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.bow);
        updateInventoryLabels();
    }

    /**
     * Handles crafting a stick if sufficient wood is available (2 pieces).
     * Updates inventory and displays a success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftStick(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.stick);
        updateInventoryLabels();
    }

    /**
     * Handles crafting a wooden knife if sufficient wood is available (4 pieces).
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftWoodenKnife(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.wooden_knife);
        updateInventoryLabels();
    }

    /**
     * Handles crafting a stone knife if sufficient wood and stone are available.
     * Requires at least 2 wood and 2 stone.
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftStoneKnife(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.stone_knife);
        updateInventoryLabels();
    }

    /**
     * Handles crafting an iron knife if sufficient wood and iron are available.
     * Requires at least 2 wood and 3 iron.
     * Updates inventory and displays success or failure message.
     * @param actionEvent The action event triggered by the UI.
     */
    public void craftIronKnife(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.iron_knife);
        updateInventoryLabels();
    }

    public void craftWoodenSword(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.wooden_sword);
        updateInventoryLabels();
    }

    public void craftStoneSword(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.stone_sword);
        updateInventoryLabels();
    }

    public void craftIronSword(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.iron_sword);
        updateInventoryLabels();
    }

    public void craftWoodenSabre(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.iron_knife);
        updateInventoryLabels();
    }

    public void craftStoneSabre(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.stone_sabre);
        updateInventoryLabels();
    }

    public void craftIronSabre(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.iron_sabre);
        updateInventoryLabels();
    }

    public void craftWoodenAxe(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.wooden_axe);
        updateInventoryLabels();
    }

    public void craftStoneAxe(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.stone_axe);
        updateInventoryLabels();
    }

    public void craftIronAxe(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.iron_axe);
        updateInventoryLabels();
    }


    public void craftWoodenPickaxe(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.wooden_pickaxe);
        updateInventoryLabels();
    }

    public void craftStonePickaxe(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.stone_pickaxe);
        updateInventoryLabels();
    }

    public void craftIronPickaxe(ActionEvent actionEvent) {
        Craft.getInstance().craftWeapon(Usuables.iron_pickaxe);
        updateInventoryLabels();
    }
}
