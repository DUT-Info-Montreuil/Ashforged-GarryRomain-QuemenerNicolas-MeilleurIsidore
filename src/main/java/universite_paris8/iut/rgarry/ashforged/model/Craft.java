package universite_paris8.iut.rgarry.ashforged.model;

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
        for(Usuable key:weapon.getComponents().keySet()){
            if(!(character.getInventory().getInventory().get(key)>=weapon.getComponents().get(key))){
                craftAutoriser=false;
            }
        }
        if(craftAutoriser){
            for(Usuable key:weapon.getComponents().keySet()){
                for(int i = 0; i<weapon.getComponents().get(key);i++){
                    character.getInventory().removeFromInventory(key);
                }
            }
        }
        character.getInventory().addToInventory(weapon);
    }
}
