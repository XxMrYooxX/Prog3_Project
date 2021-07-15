package de.htwsaar.pong.zuse.model;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

public class GameSubScene extends SubScene {
    public GameSubScene(int width, int height, int x, int y){

        //Aufrufen des Konstruktors der Oberklasse Subscene -> SubScene (root, width, height)
        //Erschafft eine neue Anchorpane als SubScene
        super(new AnchorPane(), width, height);

        //Layout Einstellungen
        this.prefHeight(height);
        this.prefWidth(width);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    //Liefert Root (AnchorPane) zurück, damit von außerhalb Elemente verändert werden können
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
