package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import java.util.HashMap;

public class Consumables extends Item {
    private String name;
    private int weight;
    private int power;
    private int durability;
    private boolean breakable;
    private int id;
    private String imagePath;
    private HashMap<Usuable, Integer> components;



    public Consumables(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath);
        this.weight = weight;
        this.power = power;
        this.durability = durability;
        this.breakable = breakable;
        this.components = new HashMap<Usuable, Integer>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getWeight() {
        return weight;
    }

    public int getPower() {
        return power;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public HashMap<Usuable, Integer> getComponents() {
        return components;
    }

    public void createGround(){
        Consumables ground = new Consumables(Usuable.ground.getId(), Usuable.ground.getName(),
                Usuable.ground.getImagePath(), Usuable.ground.getWeight(), Usuable.ground.getPower(),
                Usuable.ground.getDurability(), Usuable.ground.isBreakable(), Usuable.ground.getComponents());
    }

    public void createWood(){
        Consumables wood = new Consumables(Usuable.wood.getId(), Usuable.wood.getName(),
                Usuable.wood.getImagePath(), Usuable.wood.getWeight(), Usuable.wood.getPower(),
                Usuable.wood.getDurability(), Usuable.wood.isBreakable(), Usuable.wood.getComponents());
    }

    public void createStone(){
        Consumables stone = new Consumables(Usuable.stone.getId(), Usuable.stone.getName(),
                Usuable.stone.getImagePath(), Usuable.stone.getWeight(), Usuable.stone.getPower(),
                Usuable.stone.getDurability(), Usuable.stone.isBreakable(), Usuable.stone.getComponents());
    }

    public void createIron(){
        Consumables iron = new Consumables(Usuable.iron.getId(), Usuable.iron.getName(),
                Usuable.iron.getImagePath(), Usuable.iron.getWeight(), Usuable.iron.getPower(),
                Usuable.iron.getDurability(), Usuable.iron.isBreakable(), Usuable.iron.getComponents());
    }

    public void createAlluminium(){
        Consumables alluminium = new Consumables(Usuable.alluminium.getId(), Usuable.alluminium.getName(),
                Usuable.alluminium.getImagePath(), Usuable.alluminium.getWeight(), Usuable.alluminium.getPower(),
                Usuable.alluminium.getDurability(), Usuable.alluminium.isBreakable(), Usuable.alluminium.getComponents());
    }

    public void createCanonPowder(){
        Consumables canon_powder = new Consumables(Usuable.canon_powder.getId(), Usuable.canon_powder.getName(),
                Usuable.canon_powder.getImagePath(), Usuable.canon_powder.getWeight(), Usuable.canon_powder.getPower(),
                Usuable.canon_powder.getDurability(), Usuable.canon_powder.isBreakable(), Usuable.canon_powder.getComponents());
    }

    public void createPerlimpinpinPowder(){
        Consumables perlimpinpin_powder = new Consumables(Usuable.perlimpinpin_powder.getId(), Usuable.perlimpinpin_powder.getName(),
                Usuable.perlimpinpin_powder.getImagePath(), Usuable.perlimpinpin_powder.getWeight(), Usuable.perlimpinpin_powder.getPower(),
                Usuable.perlimpinpin_powder.getDurability(), Usuable.perlimpinpin_powder.isBreakable(), Usuable.perlimpinpin_powder.getComponents());
    }

    public void createFeather(){
        Consumables feather = new Consumables(Usuable.feather.getId(), Usuable.feather.getName(),
                Usuable.feather.getImagePath(), Usuable.feather.getWeight(), Usuable.feather.getPower(),
                Usuable.feather.getDurability(), Usuable.feather.isBreakable(), Usuable.feather.getComponents());
    }

    public void createBall(){
        Consumables ball = new Consumables(Usuable.ball.getId(), Usuable.ball.getName(),
                Usuable.ball.getImagePath(), Usuable.ball.getWeight(), Usuable.ball.getPower(),
                Usuable.ball.getDurability(), Usuable.ball.isBreakable(), Usuable.ball.getComponents());
    }

    public void createString(){
        Consumables string = new Consumables(Usuable.string.getId(), Usuable.string.getName(),
                Usuable.string.getImagePath(), Usuable.string.getWeight(), Usuable.string.getPower(),
                Usuable.string.getDurability(), Usuable.string.isBreakable(), Usuable.string.getComponents());
    }

    public void createCoal(){
        Consumables coal = new Consumables(Usuable.coal.getId(), Usuable.coal.getName(),
                Usuable.coal.getImagePath(), Usuable.coal.getWeight(), Usuable.coal.getPower(),
                Usuable.coal.getDurability(), Usuable.coal.isBreakable(), Usuable.coal.getComponents());
    }

    public void createEnchantedMineral(){
        Consumables enchanted_mineral = new Consumables(Usuable.enchanted_mineral.getId(), Usuable.enchanted_mineral.getName(),
                Usuable.enchanted_mineral.getImagePath(), Usuable.enchanted_mineral.getWeight(), Usuable.enchanted_mineral.getPower(),
                Usuable.enchanted_mineral.getDurability(), Usuable.enchanted_mineral.isBreakable(), Usuable.enchanted_mineral.getComponents());
    }

    public void createGoldenPiece(){
        Consumables golden_piece = new Consumables(Usuable.golden_piece.getId(), Usuable.golden_piece.getName(),
                Usuable.golden_piece.getImagePath(), Usuable.golden_piece.getWeight(), Usuable.golden_piece.getPower(),
                Usuable.golden_piece.getDurability(), Usuable.golden_piece.isBreakable(), Usuable.golden_piece.getComponents());
    }

    public void createCraftTable(){
        Consumables craft_table = new Consumables(Usuable.craft_table.getId(), Usuable.craft_table.getName(),
                Usuable.craft_table.getImagePath(), Usuable.craft_table.getWeight(), Usuable.craft_table.getPower(),
                Usuable.craft_table.getDurability(), Usuable.craft_table.isBreakable(), Usuable.craft_table.getComponents());
    }
}
