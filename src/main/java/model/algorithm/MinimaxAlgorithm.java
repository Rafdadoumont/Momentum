package model.algorithm;

import model.PlayerEnum;

import java.util.List;

public class MinimaxAlgorithm extends AlgorithmTemplate{

    @Override
    public byte[] getAction(PlayerEnum playerTurn, byte[][] board) {
        return new byte[0];
    }

    public int minimax(Node node, byte depth, PlayerEnum player) {
        if (depth == 0) {
            int evaluation = evaluateNode(node, player);
            node.setEvaluation(evaluation);
            return evaluation;
        }

        if (depth % 2 != 0) {
            int maxEval = Integer.MIN_VALUE;
            List<byte[]> actions = getActions(node.getBoard());

            for (byte[] action : actions) {
                byte[][] resultBoard = getResult(node.getBoard(), action, player);
                Node child = new Node(resultBoard, action);
                node.addChild(child);
                int eval = minimax(child, (byte) (depth - 1), player);
                maxEval = Integer.max(maxEval, eval);
            }
            node.setEvaluation(maxEval);
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            List<byte[]> actions = getActions(node.getBoard());

            for (byte[] action : actions) {
                byte[][] resultBoard = getResult(node.getBoard(), action, player);
                Node child = new Node(resultBoard, action);
                node.addChild(child);
                int eval = minimax(child, (byte) (depth - 1), player);
                minEval = Integer.min(minEval, eval);
            }
            node.setEvaluation(minEval);
            return minEval;
        }
    }
}
