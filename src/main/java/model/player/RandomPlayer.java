package model.player;

import controller.CPUBoardController;
import javafx.application.Platform;
import model.Game;
import model.PlayerEnum;
import model.SingleThreadExecutor;
import model.algorithm.RandomAlgorithm;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RandomPlayer implements Player, CPUPlayer {
    RandomAlgorithm randomAlgorithm;
    Executor executor;
    CPUBoardController cpuBoardController;
    Game game;

    public RandomPlayer(Game game) {
        this.game = game;
        this.randomAlgorithm = new RandomAlgorithm();
        this.executor = new SingleThreadExecutor();
    }

    @Override
    public void setCpuBoardController(CPUBoardController cpuBoardController) {
        this.cpuBoardController = cpuBoardController;
    }

    @Override
    public void getMove(MovePlayedCallback callback, long timeOut) {
        final byte[][] bestMove = {new byte[1]};

        Runnable moveCalculation = () -> bestMove[0] = randomAlgorithm.getAction(game.getTurnPlayer(), game.getBoard());

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.execute(moveCalculation);
        executorService.schedule(() -> {
            executorService.shutdown();
            Platform.runLater(() -> callback.onSuccess(bestMove[0]));
        }, timeOut - 500, TimeUnit.MILLISECONDS);

    }

    @Override
    public PlayerTypeEnum getPlayerType() {
        return PlayerTypeEnum.RANDOM;
    }
}
