package model;

import model.exceptions.InvalidPlayersException;
import model.exceptions.StateException;
import model.player.Player;
import model.state.StateContext;

import java.util.*;

public class Game implements Subject {
    public static final int DIMENSION = 7;
    public static final int MARBLE_COUNT = 8;
    private final byte[][] board;
    private Player playerOne;
    private Player playerTwo;
    private final Map<GameEventEnum, List<Observer>> observerMap;

    private Exception exception;
    private int round;
    private Player turnPlayer;
    private int playerOneMarbleCount;
    private int playerTwoMarbleCount;
    private final StateContext context;

    public Game() {
        board = new byte[DIMENSION][DIMENSION];
        round = 0;
        playerOneMarbleCount = MARBLE_COUNT;
        playerTwoMarbleCount = MARBLE_COUNT;
        this.observerMap = new HashMap<>();
        context = new StateContext();

        for (byte[] row : board) {
            Arrays.fill(row, (byte) 0);
        }
    }

    public byte[][] getBoard() {
        return board;
    }

    public int getRound() {
        return round;
    }

    public int getPlayerOneMarbleCount() {
        return playerOneMarbleCount;
    }

    public int getPlayerTwoMarbleCount() {
        return playerTwoMarbleCount;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
        turnPlayer = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void start() {
        if (playerOne == null || playerTwo == null ) {
            exception = new InvalidPlayersException("Invalid Players");
            notifyObservers(GameEventEnum.EXCEPTION_THROWN);
        } else {
            try {
                context.getState().start(context);
                notifyObservers(GameEventEnum.START_GAME);
                while (!hasPlayerWon()) {
                    playMove();
                }
                finish();
            } catch (StateException e) {
                exception = e;
                notifyObservers(GameEventEnum.EXCEPTION_THROWN);
            }
        }
    }

    public void playMove() {
        byte[] move;

        if (turnPlayer == playerOne) {
            move = playerOne.getMove(1);
        } else {
            move = playerTwo.getMove(1);
        }

        byte x = move[0];
        byte y = move[1];

        if (!isWithinBounds(x, y)) {
            System.err.println("Move is not within bounds.");
        }
        if (!isEmpty(x, y)) {
            System.out.println("Square is not empty");
        }

        if (round % 2 == 0) {
            board[y][x] = (byte) 1;
            playerOneMarbleCount--;
        } else {
            board[y][x] = (byte) 2;
            playerTwoMarbleCount--;
        }

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
                            if (board[ny - dy][nx - dx] == 1) {
                                playerOneMarbleCount++;
                            } else {
                                playerTwoMarbleCount++;
                            }
                            board[ny - dy][nx - dx] = 0;
                        } else if (board[ny][nx] == 0) {
                            board[ny][nx] = board[ny - dy][nx - dx];
                            board[ny - dy][nx - dx] = 0;
                        }

                    }

                }
            }
        }
        round++;
        notifyObservers(GameEventEnum.MOVE_PLAYED);
    }

    public void finishEarly() {
        try {
            context.getState().finishEarly(context);
            notifyObservers(GameEventEnum.FINISH_GAME);
        } catch (StateException e) {
            exception = e;
            notifyObservers(GameEventEnum.EXCEPTION_THROWN);
        }
    }

    public void finish() {
        try {
            context.getState().finish(context);
            notifyObservers(GameEventEnum.FINISH_GAME);
        } catch (StateException e) {
            exception = e;
            notifyObservers(GameEventEnum.EXCEPTION_THROWN);
        }
    }

    public void reset() {
        try {
            context.getState().reset(context);

            playerOne = null;
            playerTwo = null;

            playerOneMarbleCount = MARBLE_COUNT;
            playerTwoMarbleCount = MARBLE_COUNT;

            for (byte[] row : board) {
                Arrays.fill(row, (byte) 0);
            }
            notifyObservers(GameEventEnum.RESET_GAME);
        } catch (StateException e) {
            exception = e;
            notifyObservers(GameEventEnum.EXCEPTION_THROWN);
        }
    }

    public boolean hasPlayerWon() {
        return playerOneMarbleCount == 0 || playerTwoMarbleCount == 0;
    }

    public PlayerEnum getWonPlayer() {
        return playerOneMarbleCount == 0 ? PlayerEnum.PLAYER_ONE : PlayerEnum.PLAYER_TWO;
    }

    public StateContext getContext() {
        return context;
    }

    private boolean isWithinBounds(int x, int y) {
        return (x >= 0 && x < DIMENSION && y >= 0 && y < DIMENSION);
    }

    private boolean isEmpty(int x, int y) {
        return board[y][x] == 0;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public void addObserver(Observer observer, GameEventEnum event) {
        if (observerMap.containsKey(event)) {
            observerMap.get(event).add(observer);
        } else {
            observerMap.put(event, new ArrayList<>(Collections.singleton(observer)));
        }
    }

    @Override
    public void notifyObservers(GameEventEnum event) {
        if (observerMap.containsKey(event)) {
            for (Observer observer: observerMap.get(event)) {
                observer.update(event);
            }
        }
    }
}
