package model.algorithm;

import model.PlayerEnum;

import java.util.List;
import java.util.Random;

public class RandomAlgorithm extends AlgorithmTemplate {


    @Override
    public byte[] getAction(PlayerEnum playerTurn, byte[][] board) {
        List<byte[]> possibleActions = getActions(board);
        Random random = new Random();
        return possibleActions.get(random.nextInt(possibleActions.size()));
    }
}
