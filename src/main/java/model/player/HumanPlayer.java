package model.player;

import controller.HumanBoardController;
import model.Game;

public class HumanPlayer implements Player {
    HumanBoardController humanBoardController;
    Game game;

    public HumanPlayer(Game game) {
        this.game = game;
    }

    public void setHumanBoardController(HumanBoardController humanBoardController) {
        this.humanBoardController = humanBoardController;
    }

    @Override
    public void getMove(MovePlayedCallback callback, long timeOut) {
        humanBoardController.getNextMove(callback);
    }

    @Override
    public PlayerTypeEnum getPlayerType() {
        return PlayerTypeEnum.HUMAN;
    }
}
