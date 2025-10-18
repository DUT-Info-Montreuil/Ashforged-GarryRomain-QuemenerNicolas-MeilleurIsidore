package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import java.util.HashMap;

public class HandWeapons extends Weapons {
    public HandWeapons(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath, weight, power, durability, breakable, components);
    }

    public void createStick(){
        HandWeapons stick = new HandWeapons(Usuable.stick.getId(), Usuable.stick.getName(),
                Usuable.stick.getImagePath(), Usuable.stick.getWeight(), Usuable.stick.getPower(),
                Usuable.stick.getDurability(), Usuable.stick.isBreakable(), Usuable.stick.getComponents());
    }

    public void createWoodenKnife(){
        HandWeapons wooden_knife = new HandWeapons(Usuable.wooden_knife.getId(), Usuable.wooden_knife.getName(),
                Usuable.wooden_knife.getImagePath(), Usuable.wooden_knife.getWeight(), Usuable.wooden_knife.getPower(),
                Usuable.wooden_knife.getDurability(), Usuable.wooden_knife.isBreakable(), Usuable.wooden_knife.getComponents());
    }

    public void createStoneKnife(){
        HandWeapons stone_knife = new HandWeapons(Usuable.stone_knife.getId(), Usuable.stone_knife.getName(),
                Usuable.stone_knife.getImagePath(), Usuable.stone_knife.getWeight(), Usuable.stone_knife.getPower(),
                Usuable.stone_knife.getDurability(), Usuable.stone_knife.isBreakable(), Usuable.stone_knife.getComponents());
    }

    public void createIronKnife(){
        HandWeapons iron_knife = new HandWeapons(Usuable.iron_knife.getId(), Usuable.iron_knife.getName(),
                Usuable.iron_knife.getImagePath(), Usuable.iron_knife.getWeight(), Usuable.iron_knife.getPower(),
                Usuable.iron_knife.getDurability(), Usuable.iron_knife.isBreakable(), Usuable.iron_knife.getComponents());
    }

    public void createWoodenSword(){
        HandWeapons wooden_sword = new HandWeapons(Usuable.wooden_sword.getId(), Usuable.wooden_sword.getName(),
                Usuable.wooden_sword.getImagePath(), Usuable.wooden_sword.getWeight(), Usuable.wooden_sword.getPower(),
                Usuable.wooden_sword.getDurability(), Usuable.wooden_sword.isBreakable(), Usuable.wooden_sword.getComponents());
    }

    public void createStoneSword(){
        HandWeapons stone_sword = new HandWeapons(Usuable.stone_sword.getId(), Usuable.stone_sword.getName(),
                Usuable.stone_sword.getImagePath(), Usuable.stone_sword.getWeight(), Usuable.stone_sword.getPower(),
                Usuable.stone_sword.getDurability(), Usuable.stone_sword.isBreakable(), Usuable.stone_sword.getComponents());
    }

    public void createIronSword(){
        HandWeapons iron_sword = new HandWeapons(Usuable.iron_sword.getId(), Usuable.iron_sword.getName(),
                Usuable.iron_sword.getImagePath(), Usuable.iron_sword.getWeight(), Usuable.iron_sword.getPower(),
                Usuable.iron_sword.getDurability(), Usuable.iron_sword.isBreakable(), Usuable.iron_sword.getComponents());
    }

    public void createWoodenSabre(){
        HandWeapons wooden_sabre = new HandWeapons(Usuable.wooden_sabre.getId(), Usuable.wooden_sabre.getName(),
                Usuable.wooden_sabre.getImagePath(), Usuable.wooden_sabre.getWeight(), Usuable.wooden_sabre.getPower(),
                Usuable.wooden_sabre.getDurability(), Usuable.wooden_sabre.isBreakable(), Usuable.wooden_sabre.getComponents());
    }

    public void createStoneSabre(){
        HandWeapons stone_sabre = new HandWeapons(Usuable.stone_sabre.getId(), Usuable.stone_sabre.getName(),
                Usuable.stone_sabre.getImagePath(), Usuable.stone_sabre.getWeight(), Usuable.stone_sabre.getPower(),
                Usuable.stone_sabre.getDurability(), Usuable.stone_sabre.isBreakable(), Usuable.stone_sabre.getComponents());
    }

    public void createIronSabre(){
        HandWeapons iron_sabre = new HandWeapons(Usuable.iron_sabre.getId(), Usuable.iron_sabre.getName(),
                Usuable.iron_sabre.getImagePath(), Usuable.iron_sabre.getWeight(), Usuable.iron_sabre.getPower(),
                Usuable.iron_sabre.getDurability(), Usuable.iron_sabre.isBreakable(), Usuable.iron_sabre.getComponents());
    }

    public void createWoodenAxe(){
        HandWeapons wooden_axe = new HandWeapons(Usuable.wooden_axe.getId(), Usuable.wooden_axe.getName(),
                Usuable.wooden_axe.getImagePath(), Usuable.wooden_axe.getWeight(), Usuable.wooden_axe.getPower(),
                Usuable.wooden_axe.getDurability(), Usuable.wooden_axe.isBreakable(), Usuable.wooden_axe.getComponents());
    }

    public void createStoneAxe(){
        HandWeapons stone_axe = new HandWeapons(Usuable.stone_axe.getId(), Usuable.stone_axe.getName(),
                Usuable.stone_axe.getImagePath(), Usuable.stone_axe.getWeight(), Usuable.stone_axe.getPower(),
                Usuable.stone_axe.getDurability(), Usuable.stone_axe.isBreakable(), Usuable.stone_axe.getComponents());
    }

    public void createIronAxe(){
        HandWeapons iron_axe = new HandWeapons(Usuable.iron_axe.getId(), Usuable.iron_axe.getName(),
                Usuable.iron_axe.getImagePath(), Usuable.iron_axe.getWeight(), Usuable.iron_axe.getPower(),
                Usuable.iron_axe.getDurability(), Usuable.iron_axe.isBreakable(), Usuable.iron_axe.getComponents());
    }

    public void createWoodenPickaxe(){
        HandWeapons wooden_pickaxe = new HandWeapons(Usuable.wooden_pickaxe.getId(), Usuable.wooden_pickaxe.getName(),
                Usuable.wooden_pickaxe.getImagePath(), Usuable.wooden_pickaxe.getWeight(), Usuable.wooden_pickaxe.getPower(),
                Usuable.wooden_pickaxe.getDurability(), Usuable.wooden_pickaxe.isBreakable(), Usuable.wooden_pickaxe.getComponents());
    }

    public void createStonePickaxe(){
        HandWeapons stone_pickaxe = new HandWeapons(Usuable.stone_pickaxe.getId(), Usuable.stone_pickaxe.getName(),
                Usuable.stone_pickaxe.getImagePath(), Usuable.stone_pickaxe.getWeight(), Usuable.stone_pickaxe.getPower(),
                Usuable.stone_pickaxe.getDurability(), Usuable.stone_pickaxe.isBreakable(), Usuable.stone_pickaxe.getComponents());
    }

    public void createIronPickaxe(){
        HandWeapons iron_pickaxe = new HandWeapons(Usuable.iron_pickaxe.getId(), Usuable.iron_pickaxe.getName(),
                Usuable.iron_pickaxe.getImagePath(), Usuable.iron_pickaxe.getWeight(), Usuable.iron_pickaxe.getPower(),
                Usuable.iron_pickaxe.getDurability(), Usuable.iron_pickaxe.isBreakable(), Usuable.iron_pickaxe.getComponents());
    }

    public void createFireArm(){
        HandWeapons firearm = new HandWeapons(Usuable.firearm.getId(), Usuable.firearm.getName(),
                Usuable.firearm.getImagePath(), Usuable.firearm.getWeight(), Usuable.firearm.getPower(),
                Usuable.firearm.getDurability(), Usuable.firearm.isBreakable(), Usuable.firearm.getComponents());
    }

    public void createBomb(){
        HandWeapons bomb = new HandWeapons(Usuable.bomb.getId(), Usuable.bomb.getName(),
                Usuable.bomb.getImagePath(), Usuable.bomb.getWeight(), Usuable.bomb.getPower(),
                Usuable.bomb.getDurability(), Usuable.bomb.isBreakable(), Usuable.bomb.getComponents());
    }

    public void createEnma(){
        HandWeapons enma = new HandWeapons(Usuable.enma.getId(), Usuable.enma.getName(),
                Usuable.enma.getImagePath(), Usuable.enma.getWeight(), Usuable.enma.getPower(),
                Usuable.enma.getDurability(), Usuable.enma.isBreakable(), Usuable.enma.getComponents());
    }
}

