package model.algorithm;

public class Node {
    private final Node parent;
    private final byte[][] board;
    private final byte turn;
    private final byte[] move;

    public Node(Node parent, byte[][] board, byte[] move) {
        this.parent = parent;
        this.board = board;
        this.move = move;

        if (parent == null) {
            this.turn = 1;
        } else {
            this.turn = (byte) ((parent.getTurn() == 1) ? 2 : 1);
        }
    }

    public Node getParent() {
        return parent;
    }

    public byte[][] getBoard() {
        return board;
    }

    public byte getTurn() {
        return turn;
    }

    public byte[] getMove() {
        return move;
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
