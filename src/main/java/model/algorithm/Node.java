package model.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children;
    private final byte[][] board;
    private final byte[] move;
    private int evaluation;

    public Node(byte[][] board, byte[] move) {
        this.board = board;
        this.move = move;
        children = new ArrayList<>();
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }

    public byte[][] getBoard() {
        return board;
    }

    public byte[] getMove() {
        return move;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte[] row : board) {
            for (byte square : row) {
                stringBuilder.append(square).append(" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
