package model.algorithm;

import model.Game;
import model.PlayerEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AlgorithmTemplate {
    protected abstract byte[] getAction(PlayerEnum playerTurn, byte[][] board);


    public final byte evaluateNode(Node node, PlayerEnum player) {
        byte[][] board = node.getBoard();
        PlayerEnum opponent = (player == PlayerEnum.PLAYER_ONE) ? PlayerEnum.PLAYER_TWO : PlayerEnum.PLAYER_ONE;

        // Check for wins for the current player
        if (isTerminal(board, player)) {
            return 100; // High value indicating a win
        }

        // Check for wins for the opponent
        if (isTerminal(board, opponent)) {
            return -100; // High negative value indicating a loss for the player
        }

        // Simple positional evaluation
        int evaluation = 0;
        for (byte y = 0; y < 7; y++) {
            for (byte x = 0; x < 7; x++) {
                if (board[y][x] == player.getTurn()) {
                    // Add weight for player's tokens
                    evaluation += getPositionalWeight(x, y);
                } else if (board[y][x] == opponent.getTurn()) {
                    // Subtract weight for opponent's tokens
                    evaluation -= getPositionalWeight(x, y);
                }
            }
        }

        return (byte) evaluation;
    }

    private int getPositionalWeight(int x, int y) {
        // Assign different weights based on position
        // Example weights for a Connect Four game
        int[][] weights = {
                {3, 3, 3, 3, 3, 3, 3},
                {3, 4, 4, 4, 4, 4, 3},
                {3, 4, 5, 5, 5, 4, 3},
                {3, 4, 5, 6, 5, 4, 3},
                {3, 4, 5, 5, 5, 4, 3},
                {3, 4, 4, 4, 4, 4, 3},
                {3, 3, 3, 3, 3, 3, 3}
        };
        return weights[y][x];
    }

    protected final List<byte[]> getActions(byte[][] board) {
        List<byte[]> moves = new ArrayList<>();

        for (byte y = 0; y < 7; y++) {
            for (byte x = 0; x < 7; x++) {
                if (board[y][x] == 0) {
                    moves.add(new byte[]{x, y});
                }
            }
        }
        return moves;
    }

    protected final byte[][] getResult(byte[][] board, byte[] action, PlayerEnum player) {
        byte[][] newBoard = copyOf(board);
        byte x = action[0];
        byte y = action[1];
        newBoard[y][x] = player.getTurn();

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
        return newBoard;
    }

    protected boolean isTerminal(byte[][] board, PlayerEnum player) {
        byte sum = 0;
        for (byte[] row: board) {
            for (byte square: row) {
                if (square == player.getTurn()) {
                    sum++;
                }
            }
        }
        return sum == Game.MARBLE_COUNT;
    }

    protected boolean isWithinBounds(byte x, byte y) {
        return x >= 0 && y >= 0 && x < 7 && y < 7;
    }

    protected byte[][] copyOf(byte[][] board) {
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
