package model.algorithm;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomAlgorithm extends AlgorithmTemplate {

    @Override
    public Node findBestChildNode(boolean isMaxPlayer, byte[][] board) {
        Node rootNode = new Node(null, board, null);
        List<Node> successors = getSuccessors(rootNode);
        Random random = new Random();
        return successors.get(random.nextInt(successors.size()));
    }
}
