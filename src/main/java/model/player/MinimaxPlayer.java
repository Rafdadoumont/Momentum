package model.player;

import controller.CPUBoardController;
import javafx.application.Platform;
import model.Game;
import model.PlayerEnum;
import model.SingleThreadExecutor;
import model.algorithm.MinimaxAlgorithm;
import model.algorithm.Node;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MinimaxPlayer implements Player, CPUPlayer {
    MinimaxAlgorithm minimaxAlgorithm;
    Executor executor;
    CPUBoardController cpuBoardController;
    Game game;

    public MinimaxPlayer(Game game) {
        this.game = game;
        this.minimaxAlgorithm = new MinimaxAlgorithm();
        this.executor = new SingleThreadExecutor();
    }

    @Override
    public void setCpuBoardController(CPUBoardController cpuBoardController) {
        this.cpuBoardController = cpuBoardController;
    }

    @Override
    public void getMove(MovePlayedCallback callback, long timeOut) {
        Node node = new Node(game.getBoard(), null);
        final byte[][] bestMove = {new byte[1]};
        final int[] currentDepth = {3};
        final int[] maxEval = {Integer.MIN_VALUE};

        Runnable moveCalculation = () -> {
            minimaxAlgorithm.minimax(node, (byte) currentDepth[0], true, PlayerEnum.PLAYER_TWO);
            List<Node> children = node.getChildren();

            for (Node child : children) {
                if (child.getEvaluation() > maxEval[0]) {
                    System.out.println(child.getEvaluation());
                    maxEval[0] = child.getEvaluation();
                    bestMove[0] = child.getMove();
                }
            }
            currentDepth[0]++;
        };

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
