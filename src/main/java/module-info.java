module universite_paris8.iut.rgarry.ashforged {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.desktop;

    opens universite_paris8.iut.rgarry.ashforged to javafx.fxml;
    exports universite_paris8.iut.rgarry.ashforged;
    exports universite_paris8.iut.rgarry.ashforged.Controller;
    opens universite_paris8.iut.rgarry.ashforged.Controller to javafx.fxml;
    exports universite_paris8.iut.rgarry.ashforged.model;
    opens universite_paris8.iut.rgarry.ashforged.model to javafx.fxml;
}