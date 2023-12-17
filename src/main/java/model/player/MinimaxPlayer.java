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
import java.util.concurrent.*;

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
        final int[] currentDepth = {1};
        final int[] maxEval = {Integer.MIN_VALUE};
        final long startTime = System.currentTimeMillis();


        Runnable moveCalculation = () -> {
            while (System.currentTimeMillis() - startTime < timeOut) {
                minimaxAlgorithm.minimax(node, (byte) currentDepth[0], PlayerEnum.PLAYER_TWO);
                List<Node> children = node.getChildren();

                for (Node child : children) {
                    if (child.getEvaluation() > maxEval[0]) {
                        maxEval[0] = child.getEvaluation();
                        bestMove[0] = child.getMove();
                    }
                }
                System.out.println("Depth: " + currentDepth[0]);
                currentDepth[0]++;
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Future<?> future = executor.submit(moveCalculation);
        Runnable cancelTask = () -> {
            System.out.println("Cancel task");
            future.cancel(true);
            System.out.println("Interrupt thread");
            Thread.currentThread().interrupt(); // Interrupt the thread
            Platform.runLater(() -> callback.onSuccess(bestMove[0]));
        };

        executor.schedule(cancelTask, timeOut, TimeUnit.MILLISECONDS);
        executor.shutdown();
    }

    @Override
    public PlayerTypeEnum getPlayerType() {
        return PlayerTypeEnum.RANDOM;
    }
}
