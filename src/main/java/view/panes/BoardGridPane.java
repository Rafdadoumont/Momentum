package view.panes;

import controller.BoardGridPaneController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Game;
import model.PlayerEnum;
import view.components.Marble;
import view.components.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoardGridPane extends GridPane {
    BoardGridPaneController boardGridPaneController;
    byte[] pressedSquare;

    public BoardGridPane() {
        setAlignment(Pos.CENTER);
        setDisable(true);
        createBoard();
    }

    public void setBoardGridController(BoardGridPaneController boardGridPaneController) {
        this.boardGridPaneController = boardGridPaneController;
    }

    public Collection<Square> getAllSquares() {
        List<Square> squares = new ArrayList<>();
        for (Node node : getChildren()) {
            if (node instanceof Square) {
                squares.add((Square) node);
            }
        }
        return squares;
    }

    private void createBoard() {
        for (byte row = 0; row < Game.DIMENSION; row++) {
            for (byte col = 0; col < Game.DIMENSION; col++) {
                final byte currentCol = col;
                final byte currentRow = row;

                Square square = new Square(col, row);
                if ((row + col) % 2 == 0) {
                    square.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
                }
//                square.setOnMouseClicked(e -> {
//                    pressedSquare = new byte[]{currentCol, currentRow};
//                });

                this.add(square, col, row);
            }
        }
    }

    public void updateBoard() {
        byte[][] board = boardGridPaneController.getBoard();

        for (int row = 0; row < Game.DIMENSION; row++) {
            for (int col = 0; col < Game.DIMENSION; col++) {
                Square square = (Square) getChildren().get(row * Game.DIMENSION + col);

                if (board[row][col] != 0) {
                    if (board[row][col] == 1) {
                        square.placeMarble(new Marble(PlayerEnum.PLAYER_ONE));
                    } else {
                        square.placeMarble(new Marble(PlayerEnum.PLAYER_TWO));
                    }
                } else {
                    square.clearMarble();
                }
            }
        }
    }

    public void resetBoard() {
        for (int row = 0; row < Game.DIMENSION; row++) {
            for (int col = 0; col < Game.DIMENSION; col++) {
                Square square = (Square) getChildren().get(row * Game.DIMENSION + col);
                square.getChildren().clear();
            }
        }
    }

    private byte[] getCoordinates(Square square) {
        return new byte[]{square.getX(), square.getY()};
    }

    public interface OnSquareClickedListener {
        void onSquareClicked(byte[] coordinates);
    }
}
