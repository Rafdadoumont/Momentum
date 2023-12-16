package application;

import model.Game;
import model.PlayerEnum;
import model.algorithm.MinimaxAlgorithm;
import model.algorithm.Node;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        MinimaxAlgorithm algorithm = new MinimaxAlgorithm();

        Node rootNode = new Node(game.getBoard(), null);

        algorithm.minimax(rootNode, (byte) 3, true, PlayerEnum.PLAYER_ONE);

        algorithm.printTree(rootNode);





//        byte[][] customBoard = new byte[7][7];
//
//        for (byte[] row : customBoard) {
//            Arrays.fill(row, (byte) 0);
//        }
//
//        customBoard[1][1] = 1;
//        customBoard[2][1] = 1;
//        customBoard[3][1] = 1;
//        customBoard[4][1] = 1;
//        customBoard[5][1] = 1;


        //System.out.println(algorithm.evaluateNode(new Node(customBoard,new byte[]{0,0}),PlayerEnum.PLAYER_ONE));



//        algorithm.minimax(rootNode, (byte) 3, true);

    }
}