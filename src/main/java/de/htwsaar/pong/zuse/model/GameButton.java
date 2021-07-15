package de.htwsaar.pong.zuse.model;

import javafx.scene.control.Button;

public class GameButton extends Button {

    private static final String GAME = "-fx-border-width: 2px; -fx-text-fill: black; -fx-border-color: #145374; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    public GameButton(String buttonText, int x, int y) {
        super();
        this.setText(buttonText);
        this.setScaleX(2);
        this.setScaleY(2);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

}