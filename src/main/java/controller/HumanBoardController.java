package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import model.GameEventEnum;
import model.Observer;
import model.player.HumanPlayer;
import model.player.Player;
import view.components.Square;
import view.panes.BoardGridPane;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class HumanBoardController implements Observer {
    HumanPlayer player;
    BoardGridPane boardGridPane;
    private CompletableFuture<byte[]> waitForMove;

    private final EventHandler<MouseEvent> squareClickHandler = this::handleSquareClick;

    public HumanBoardController(Player player, BoardGridPane boardGridPane) {
        this.player = (HumanPlayer) player;
        this.boardGridPane = boardGridPane;
    }

    public CompletableFuture<byte[]> getNextMove() {
        boardGridPane.setDisable(false);

        long currentMillis = System.currentTimeMillis();
        Platform.runLater(() -> {
            long newMillis = System.currentTimeMillis();
            while (newMillis - currentMillis < 1000){
                newMillis = System.currentTimeMillis();
            }
        });


        waitForMove = new CompletableFuture<>();

        Collection<Square> squares = boardGridPane.getAllSquares();
        for (Square square: squares) {
            square.addEventHandler(MouseEvent.MOUSE_CLICKED, squareClickHandler);
        }

        return waitForMove;
    }

    private void handleSquareClick(MouseEvent event) {
        System.out.println("Clicked");
        Square clickedSquare = (Square) event.getSource();
        byte[] coordinates = getCoordinates(clickedSquare);
        waitForMove.complete(coordinates);
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
