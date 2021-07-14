package de.htwsaar.pong.zuse.model;

import javafx.scene.control.Button;

public class GameButton extends Button {

    private static final String GAME = "-fx-border-width: 2px; -fx-text-fill: black; -fx-border-color: #145374; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    private static final String GAME_HOVER = "-fx-border-width: 2px; -fx-text-fill: black; -fx-border-color: #ff502f; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    public GameButton(String buttonText, int x, int y){
        super(); //find ich super!
        this.setText(buttonText);
        this.setLayoutX(x);
        this.setLayoutY(y);
        addListeners();
    }
    private void addListeners()
    {
        this.setOnMouseEntered(e ->
        {
            this.setStyle(GAME_HOVER);
        });

        this.setOnMouseExited(e ->
        {
            this.setStyle(GAME);
        });
    }
}
