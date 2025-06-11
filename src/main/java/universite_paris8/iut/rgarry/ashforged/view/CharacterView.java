package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.Controller.CharacterController;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class CharacterView {
    private Pane paneperso;
    private Image persoDroite;
    private Image persoGauche;
    private ImageView perso;

    /***
     * Permet d'instancier le sprite du personnage sur la map et de mettre à jour sa position
     * après chaque déplacement.
     *
     * @param paneperso
     * @param personnage
     * @param personnageController
     * @param field
     */
    public CharacterView(Pane paneperso, Character personnage, CharacterController personnageController, Field field){
        this.paneperso=paneperso;

        paneperso.setPrefSize(field.getWidth(), field.getHeight());

        this.persoDroite = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/rightHero.png").toExternalForm());
        this.persoGauche = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/leftHero.png").toExternalForm());
        this.perso = new ImageView(persoDroite);

        perso.setId("perso");

        perso.translateXProperty().bind(personnage.getXProperty());
        perso.translateYProperty().bind(personnage.getYProperty());

        paneperso.getChildren().add(this.perso);
        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        personnageController.setupKeyHandlers(paneperso);
    }

    /***
     *Change le sprite en fonction de la direction du joueur.
     *
     * @param direction
     */
    public void changeSprite(char direction){
        if(direction=='r'){
            perso.setImage(persoDroite);
        }
        else if(direction=='l'){
            perso.setImage(persoGauche);
        }
    }
}
