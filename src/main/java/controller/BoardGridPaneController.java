package controller;

import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import view.panes.BoardGridPane;

public class BoardGridPaneController implements Observer {
    BoardGridPane boardGridPane;
    GameFacade gameFacade;

    public BoardGridPaneController(BoardGridPane boardGridPane, GameFacade gameFacade) {
        this.boardGridPane = boardGridPane;
        this.gameFacade = gameFacade;
        gameFacade.setBoardGridPane(boardGridPane);
    }

    public byte[][] getBoard() {
        return gameFacade.getBoard();
    }

    @Override
    public void update(GameEventEnum event) {
        switch (event) {
            case START_GAME:
                break;
            case FINISH_GAME:
                break;
            case RESET_GAME:
                boardGridPane.resetBoard();
                break;
            case MOVE_PLAYED:
                boardGridPane.updateBoard();
                break;
        }
    }
}
