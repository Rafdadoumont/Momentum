package model;

import javafx.scene.paint.Color;

public enum PlayerEnum {
    PLAYER_ONE("Player 1", Color.BLUE, (byte) 1),
    PLAYER_TWO("Player 2", Color.RED, (byte) 2);

    private final String playerName;
    private final Color color;
    private final byte turn;

    PlayerEnum(String playerName, Color color, byte turn) {
        this.playerName = playerName;
        this.color = color;
        this.turn = turn;
    }

    public Color getColor() {
        return color;
    }

    public byte getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return playerName;
    }
}

