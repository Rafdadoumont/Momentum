package model.player;

import model.Game;

public interface Player {
    void getMove(MovePlayedCallback callback, long timeOut);
    PlayerTypeEnum getPlayerType();

    interface MovePlayedCallback {
        void onSuccess(byte[] move);
    }
}
