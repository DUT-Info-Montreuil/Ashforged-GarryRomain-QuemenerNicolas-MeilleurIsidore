package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.Controller.PersonnageController;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

public class PersonnageView {
    private Pane paneperso;
    private Image persoDroite;
    private Image persoGauche;
    private ImageView perso;
    public PersonnageView(Pane paneperso, Personnage personnage, PersonnageController personnageController, Field field){
        this.paneperso=paneperso;

        paneperso.setPrefSize(field.longueur(), field.hauteur());

        this.persoDroite = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/personnage.png").toExternalForm());
        this.persoGauche = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/personnageDroite.png").toExternalForm());
        this.perso = new ImageView(persoDroite);

        this.perso.setId("perso");

        this.perso.translateXProperty().bind(personnage.getXProperty());
        this.perso.translateYProperty().bind(personnage.getYProperty());

        perso.setId("perso");

        perso.translateXProperty().bind(personnage.getXProperty());
        perso.translateYProperty().bind(personnage.getYProperty());

        paneperso.getChildren().add(this.perso);
        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        personnageController.setupKeyHandlers(paneperso);
    }

    public void changeSprite(char direction){
        for(int i = 0; i<this.paneperso.getChildren().size();i++){
            if(this.paneperso.getChildren().get(i).equals(perso)){
                this.paneperso.getChildren().remove(perso);
                if(direction=='r'){
                    perso.setImage(persoDroite);
                    this.paneperso.getChildren().add(perso);
                }
                else {
                    perso.setImage(persoGauche);
                    this.paneperso.getChildren().add(perso);
                }
            }
        }
    }
}
