package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Utilities;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class Craft {
    private static Craft craft;
    private static Character character;

    private Craft(Character character){
        this.character = character;
    }

    public void craftUsable(Utilities usuables) {
        boolean craftAutoriser=true;
        for(Usuables key:usuables.getComponents().keySet()){
            if(!(character.getInventory().getListOfInventory().get(key)>=usuables.getComponents().get(key))){
                craftAutoriser=false;
            }
        }
        if(craftAutoriser){
            for(Usuables key:usuables.getComponents().keySet()){
                for(int i = 0; i<usuables.getComponents().get(key);i++){
                    character.getInventory().removeFromInventory(key);
                }
            }
        }
        character.getInventory().addToInventory(usuables);
    }
}
