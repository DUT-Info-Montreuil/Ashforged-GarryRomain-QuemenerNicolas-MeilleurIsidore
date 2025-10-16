package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class Craft {
    private static Craft craft;
    private static Character character;

    private Craft(Character character){
        this.character = character;
    }

    public void craftWeapon(Usuable weapon) {
        boolean craftAutoriser=true;
        for(ItemInterface key:weapon.getComponents().keySet()){
            if(!(character.getInventory().getInventory().get(key)>=weapon.getComponents().get(key))){
                craftAutoriser=false;
            }
        }
        if(craftAutoriser){
            for(ItemInterface key:weapon.getComponents().keySet()){
                for(int i = 0; i<weapon.getComponents().get(key);i++){
                    character.getInventory().removeFromInventory(key);
                }
            }
        }
        character.getInventory().addToInventory(weapon);
    }

    public void craftUsable(Consomable usuable) {
        boolean craftAutoriser=true;
        for(ItemInterface key:usuable.getComponents().keySet()){
            if(!(character.getInventory().getInventory().get(key)>=usuable.getComponents().get(key))){
                craftAutoriser=false;
            }
        }
        if(craftAutoriser){
            for(ItemInterface key:usuable.getComponents().keySet()){
                for(int i = 0; i<usuable.getComponents().get(key);i++){
                    character.getInventory().removeFromInventory(key);
                }
            }
        }
    }

    public static Craft getInstance() {
        if (craft == null) {
            craft = new Craft(Character.getInstance());
        }
        return craft;
    }
}
