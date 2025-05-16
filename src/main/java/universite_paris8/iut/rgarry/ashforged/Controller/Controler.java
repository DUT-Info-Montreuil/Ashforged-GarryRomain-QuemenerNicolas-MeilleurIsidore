package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.Field;
import universite_paris8.iut.rgarry.ashforged.HelloApplication;
import universite_paris8.iut.rgarry.ashforged.character.Personnage;


import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable {

    private BooleanProperty zPressed = new SimpleBooleanProperty();
    private BooleanProperty qPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = zPressed.or(qPressed).or(sPressed).or(dPressed);

    //private int movementVariable = 2;

    @FXML
    private TilePane tilepane;
    @FXML
    private Pane paneperso;

    private Personnage personnage = HelloApplication.getPersonnage();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();
        double screenWidth = 1920;
        double screenHeight = 1080;
        field.createField(tilepane, screenWidth, screenHeight);

        paneperso.setPrefSize(field.getTailleField()[0],field.getTailleField()[1]);

        Image perso = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/personnage.png").toExternalForm());
        ImageView perso2 = new ImageView(perso);

        perso2.setId("perso");

        perso2.translateXProperty().bind(personnage.getXProperty());
        perso2.translateYProperty().bind(personnage.getYProperty());

        paneperso.getChildren().add(perso2);
    }
}