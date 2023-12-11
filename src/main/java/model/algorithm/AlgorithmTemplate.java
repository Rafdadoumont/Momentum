package model.algorithm;

import model.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AlgorithmTemplate {
    public abstract Node findBestChildNode(boolean isMaxPlayer, byte[][] board);

    public final List<Node> getSuccessors(Node node) {
        List<Node> successors = new ArrayList<>();
        byte[][] board = node.getBoard();

        // Iterate over the squares of the board
        for (byte y = 0; y < 7; y++) {
            for (byte x = 0; x < 7; x++) {
                if (board[y][x] == 0) {
                    byte[][] newBoard = copyOf(board);
                    newBoard[y][x] = node.getTurn();
                    byte[] move = new byte[]{x, y};

                    // Iterate over the neighbors
                    for (byte dy = -1; dy < 2; dy++) {
                        for (byte dx = -1; dx < 2; dx++) {
                            if (!(dx == 0 && dy == 0)) {
                                byte nx = (byte) (x + dx);
                                byte ny = (byte) (y + dy);

                                if (isWithinBounds(nx, ny) && board[ny][nx] != 0) {
                                    while (isWithinBounds(nx, ny) && board[ny][nx] != 0) {
                                        nx += dx;
                                        ny += dy;
                                    }

                                    if (!isWithinBounds(nx, ny)) {
                                        newBoard[ny - dy][nx - dx] = 0;
                                    } else if (board[ny][nx] == 0) {
                                        newBoard[ny][nx] = newBoard[ny - dy][nx - dx];
                                        newBoard[ny - dy][nx - dx] = 0;
                                    }

                                }
                            }
                        }
                    }
                    successors.add(new Node(node, newBoard, move));
                }
            }

        }
        return successors;
    }

    private boolean hasWon(byte[][] board, byte player) {
        byte sum = 0;
        for (byte[] row: board) {
            for (byte square: row) {
                if (square == player) {
                    sum++;
                }
            }
        }
        return sum == Game.MARBLE_COUNT;
    }

    private boolean isWithinBounds(byte x, byte y) {
        return x >= 0 && y >= 0 && x < 7 && y < 7;
    }

    private byte[][] copyOf(byte[][] board) {
        byte[][] boardCopy = new byte[board.length][];
        for (byte i = 0; i < board.length; i++) {
            boardCopy[i] = board[i].clone();
        }
        return boardCopy;
    }

    private void print(List<byte[][]> list) {
        for (byte[][] byteArray : list) {
            for (byte[] innerArray : byteArray) {
                System.out.println(Arrays.toString(innerArray));
            }
            System.out.println();
        }
    }
}
