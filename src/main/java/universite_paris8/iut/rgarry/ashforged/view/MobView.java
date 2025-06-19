package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.Controller.CharacterController;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

import java.util.ArrayList;
import java.util.List;

public class MobView {
    private Pane paneperso;
    private ImageView mob;
    private List<Npc> npcs = new ArrayList<>();
    private List<Mobs> mobs = new ArrayList<>();


    public MobView(Environment environment,Pane paneperso){
        this.paneperso=paneperso;

        mobs = environment.getMobs();
        npcs = environment.getNpcs();

        Image pierreImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/caseInventaire.png").toExternalForm());
        Image terreImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png").toExternalForm());
        Image paoloImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png").toExternalForm());
        Image salomeImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/leftSalome.png").toExternalForm());
        Image terryImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftTerry.png").toExternalForm());
        Image brandaImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftTerry.png").toExternalForm());
        for (Npc npc : npcs) {
            ImageView npcView;
            if (npc.getName().equals("Paolo")) {
                npcView = new ImageView(paoloImage);
            } else {
                npcView = new ImageView(pierreImage);
                if (npc.getName().equals("Branda")) {
                    npcView = new ImageView(brandaImage);
                    npc.setX(200);
                    npc.setY(200);

                } else if (npc.getName().equals("Terry")) {
                    npcView = new ImageView(terryImage);
                    npc.setX(300);
                    npc.setY(300);
                } else if (npc.getName().equals("Salome")) {
                    npcView = new ImageView(salomeImage);
                    npc.setX(400);
                    npc.setY(400);
                }
            }
            npcView.layoutXProperty().bind(npc.getXProperty().asObject());
            npcView.layoutYProperty().bind(npc.getYProperty().asObject());
            paneperso.getChildren().add(npcView);
        }

        for (Mobs mob : mobs) {
            ImageView mobView = new ImageView(terreImage);
            mobView.layoutXProperty().bind(mob.getXProperty().asObject());
            mobView.layoutYProperty().bind(mob.getYProperty().asObject());
            paneperso.getChildren().add(mobView);
        }
    }

    public List<Npc> getNpcs(){
        return npcs;
    }

    public List<Mobs> getMobs(){
        return mobs;
    }
}
