package model.player;

public interface Player {
    byte[] getMove(int seconds);
    PlayerTypeEnum getPlayerType();
}
