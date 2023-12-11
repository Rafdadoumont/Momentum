package controller;

import model.GameEventEnum;
import model.Observer;
import model.player.Player;
import view.panes.BoardGridPane;

public class CPUBoardController implements Observer {
    Player player;
    BoardGridPane boardGridPane;

    public CPUBoardController(Player player, BoardGridPane boardGridPane) {
        this.player = player;
        this.boardGridPane = boardGridPane;
    }

    @Override
    public void update(GameEventEnum event) {

    }
}
