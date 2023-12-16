package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.GameEventEnum;
import model.Observer;
import model.player.HumanPlayer;
import model.player.Player;
import view.components.Square;
import view.panes.BoardGridPane;

import java.util.Arrays;
import java.util.Collection;

public class HumanBoardController implements Observer {
    HumanPlayer player;
    BoardGridPane boardGridPane;

    public HumanBoardController(Player player, BoardGridPane boardGridPane) {
        this.player = (HumanPlayer) player;
        this.boardGridPane = boardGridPane;
    }

    public void getNextMove(Player.MovePlayedCallback callback) {
        Collection<Square> squares = boardGridPane.getAllSquares();
        for (Square square : squares) {
            EventHandler<MouseEvent> eventHandler = event -> handleSquareClick(event, callback);
            square.setOnMouseClicked(eventHandler);
        }
        boardGridPane.setDisable(false);
    }

    private void handleSquareClick(MouseEvent event, Player.MovePlayedCallback callback) {
        Square clickedSquare = (Square) event.getSource();
        byte[] coordinates = getCoordinates(clickedSquare);
        System.out.println("Human Player wants to play move: " + Arrays.toString(coordinates));

        clearSquareEventHandler();
        boardGridPane.setDisable(true);
        callback.onSuccess(coordinates);
    }

    private void clearSquareEventHandler() {
        Collection<Square> squares = boardGridPane.getAllSquares();
        for (Square square : squares) {
            square.setOnMouseClicked(null);
        }
    }

    private byte[] getCoordinates(Square square) {
        return new byte[]{square.getX(), square.getY()};
    }

    @Override
    public void update(GameEventEnum event) {
        switch (event) {
            case START_GAME, MOVE_PLAYED:

        }
    }
}
