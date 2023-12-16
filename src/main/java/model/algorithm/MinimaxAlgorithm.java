package model.algorithm;

import model.PlayerEnum;

import java.util.List;

public class MinimaxAlgorithm extends AlgorithmTemplate{

    @Override
    public byte[] getAction(PlayerEnum playerTurn, byte[][] board) {
        return new byte[0];
    }

    public int minimax(Node node, byte depth, boolean isMaximizing, PlayerEnum player) {
        if (depth == 0) {
            int evaluation = evaluateNode(node, player);
            node.setEvaluation(evaluation);
            return evaluation;
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            node.setEvaluation(Integer.MIN_VALUE);
            List<byte[]> actions = getActions(node.getBoard());

            for (byte[] action: actions) {
                byte[][] resultBoard = getResult(node.getBoard(), action, PlayerEnum.PLAYER_ONE);
                Node child = new Node(resultBoard, action);
                node.addChild(child);
                int eval = minimax(child, (byte) (depth - 1), false , player == PlayerEnum.PLAYER_ONE ? PlayerEnum.PLAYER_TWO : PlayerEnum.PLAYER_ONE);
                maxEval = Integer.max(maxEval, eval);
            }
            node.setEvaluation(maxEval);
            return maxEval;
        }

        else {
            int minEval = Integer.MAX_VALUE;
            List<byte[]> actions = getActions(node.getBoard());

            for (byte[] action: actions) {
                byte[][] resultBoard = getResult(node.getBoard(), action, PlayerEnum.PLAYER_TWO);
                Node child = new Node(resultBoard, action);
                node.addChild(child);
                int eval = minimax(child, (byte) (depth - 1), true, player == PlayerEnum.PLAYER_ONE ? PlayerEnum.PLAYER_TWO : PlayerEnum.PLAYER_ONE);
                minEval = Integer.min(minEval, eval);
            }
            node.setEvaluation(minEval);
            return minEval;
        }
    }

    public void printTree(Node root) {
        printSubtree(root, "");
    }

    private void printSubtree(Node node, String prefix) {
        if (node != null) {
            System.out.println(prefix + "└── " + node.getEvaluation());

            List<Node> children = node.getChildren();
            if (children != null && !children.isEmpty()) {
                int lastIndex = children.size() - 1;
                for (int i = 0; i < lastIndex; i++) {
                    Node child = children.get(i);
                    printSubtree(child, prefix + "    │ ");
                }
                printSubtree(children.get(lastIndex), prefix + "     ");
            }
        }
    }
}
