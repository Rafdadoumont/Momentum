package model.player;

import controller.HumanBoardController;
import model.GameFacade;

import java.util.Arrays;


public class HumanPlayer implements Player {
    HumanBoardController humanBoardController;
    GameFacade gameFacade;
    byte[] move;

    public HumanPlayer(GameFacade gameFacade) {
        //this.gameFacade = gameFacade;
    }

    public void setHumanBoardController(HumanBoardController humanBoardController) {
        this.humanBoardController = humanBoardController;
    }

    @Override
    public byte[] getMove(int seconds) {
        try {
            byte[] move = humanBoardController.getNextMove();
            return move;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PlayerTypeEnum getPlayerType() {
        return PlayerTypeEnum.HUMAN;
    }
}
