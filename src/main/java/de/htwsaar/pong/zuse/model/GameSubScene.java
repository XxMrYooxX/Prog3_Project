package de.htwsaar.pong.zuse.model;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

/**
 * Klasse GameSubScene
 * repräsentiert die SubScene für ein Game
 */
public class GameSubScene extends SubScene {
    public GameSubScene(int width, int height, int x, int y){
        //Aufrufen des Konstruktors der Oberklasse Subscene → SubScene (root, width, height)
        //erschafft eine neue AnchorPane als SubScene
        super(new AnchorPane(), width, height);
        //Layout Einstellungen
        this.prefHeight(height);
        this.prefWidth(width);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * Getter für Root-AnchorPane
     * @return Root AnchorPane
     */
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
