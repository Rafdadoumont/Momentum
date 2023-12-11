package model;

import javafx.scene.paint.Color;

public enum PlayerEnum {
    PLAYER_ONE("Player 1", Color.BLUE),
    PLAYER_TWO("Player 2", Color.RED);

    private final String playerName;
    private final Color color;

    PlayerEnum(String playerName, Color color) {
        this.playerName = playerName;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return playerName;
    }
}

