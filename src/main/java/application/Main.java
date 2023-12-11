//package application;
//
//import model.algorithm.Node;
//
//import java.util.Arrays;
//
//public class Main {
//
//    public static void main(String[] args) {
//        MiniMaxAlgorithmStrategy algorithmStrategy = new MiniMaxAlgorithmStrategy();
//
//        byte[][] board = new byte[7][7];
//
//        for (byte[] row : board) {
//            Arrays.fill(row, (byte) 0);
//        }
//
//        Node rootNode = new Node(null, board);
//
//        System.out.println("Start board");
//        System.out.println(rootNode);
//
//        algorithmStrategy.getSuccessors(rootNode);
//    }
//}