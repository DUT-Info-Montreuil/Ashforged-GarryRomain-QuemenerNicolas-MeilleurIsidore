package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.Controller.PersonnageController;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

public class PersonnageView {
    public PersonnageView(Pane paneperso, Personnage personnage, PersonnageController personnageController, Field field){
        paneperso.setPrefSize(field.longueur(), field.hauteur());

        Image perso = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/personnage.png").toExternalForm());
        ImageView perso2 = new ImageView(perso);

        perso2.setId("perso");

        perso2.translateXProperty().bind(personnage.getXProperty());
        perso2.translateYProperty().bind(personnage.getYProperty());

        paneperso.getChildren().add(perso2);
        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        personnageController.setupKeyHandlers(paneperso);
    }
}
