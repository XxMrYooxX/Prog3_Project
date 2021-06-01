package de.htwsaar.pong.zuse.model;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

public class GameSubScene extends SubScene {
    public GameSubScene(int width, int height, int x, int y){
        super(new AnchorPane(), width, height);
        this.prefHeight(height);
        this.prefWidth(width);
        this.setLayoutX(x);
        this.setLayoutY(y);
        AnchorPane root = (AnchorPane) this.getPane();
        root.setStyle("-fx-background-color: #beeef7;");
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
