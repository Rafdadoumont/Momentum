package controller;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import model.PlayerEnum;
import view.panes.PlayerPane;

public abstract class PlayerPaneController implements Observer {
    PlayerPane playerPane;
    GameFacade gameFacade;
    PlayerEnum player;

    public PlayerPaneController(PlayerPane playerPane, GameFacade gameFacade, PlayerEnum player) {
        this.playerPane = playerPane;
        this.gameFacade = gameFacade;
        this.player = player;
        setBorders();
    }

    private void setBorders() {
        Border idleBorder = new Border(new BorderStroke(player.getColor(), BorderStrokeStyle.SOLID, null, BorderStroke.DEFAULT_WIDTHS));
        playerPane.setBorder(idleBorder);
    }

    protected void updateTurnIndicator() {
        Border idleBorder = new Border(new BorderStroke(player.getColor(), BorderStrokeStyle.SOLID, null, BorderStroke.DEFAULT_WIDTHS));
        Border activeBorder = new Border(new BorderStroke(player.getColor(), BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM));

        if (gameFacade.getRound() % 2 == 0) {
            if (player == PlayerEnum.PLAYER_ONE) {
                playerPane.setBorder(activeBorder);
            } else if (player == PlayerEnum.PLAYER_TWO){
                playerPane.setBorder(idleBorder);
            }
        } else {
            if (player == PlayerEnum.PLAYER_ONE) {
                playerPane.setBorder(idleBorder);
            } else if (player == PlayerEnum.PLAYER_TWO){
                playerPane.setBorder(activeBorder);
            }
        }
    }

    @Override
    public void update(GameEventEnum event) {

        switch (event) {
            case START_GAME, MOVE_PLAYED:
                updateTurnIndicator();
                break;
            case FINISH_GAME:
                setBorders();
                break;
        }
    }
}
