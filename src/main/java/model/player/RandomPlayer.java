package model.player;

import controller.CPUBoardController;
import model.GameFacade;
import model.algorithm.RandomAlgorithm;

import java.util.concurrent.TimeUnit;

public class RandomPlayer implements Player, CPUPlayer {
    RandomAlgorithm randomAlgorithm;
    CPUBoardController cpuBoardController;
    GameFacade gameFacade;

    public RandomPlayer(GameFacade gameFacade) {
        this.randomAlgorithm = new RandomAlgorithm();
        this.gameFacade = gameFacade;
    }

    public void setCpuBoardController(CPUBoardController cpuBoardController) {
        this.cpuBoardController = cpuBoardController;
    }

    @Override
    public byte[] getMove(int seconds) {
        return randomAlgorithm.findBestChildNode(true, gameFacade.getBoard()).getMove();
    }

    @Override
    public PlayerTypeEnum getPlayerType() {
        return PlayerTypeEnum.RANDOM;
    }
}
