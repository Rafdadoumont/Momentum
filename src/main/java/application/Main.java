package application;

import model.Game;
import model.PlayerEnum;
import model.TreePrinter;
import model.algorithm.MinimaxAlgorithm;
import model.algorithm.Node;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        MinimaxAlgorithm algorithm = new MinimaxAlgorithm();

        Node rootNode = new Node(game.getBoard(), null);

        algorithm.minimax(rootNode, (byte) 3, PlayerEnum.PLAYER_ONE);

        TreePrinter.printTree(rootNode);
    }
}