package controller;

import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import model.PlayerEnum;
import view.GameView;

public class GameViewController implements Observer {
    GameView gameView;
    GameFacade gameFacade;

    public GameViewController(GameView gameView, GameFacade gameFacade) {
        this.gameView = gameView;
        this.gameFacade = gameFacade;
    }

    private void setPlayerPanes() {
        gameView.setLeftPane(gameFacade.getPlayerPane(PlayerEnum.PLAYER_ONE));
        gameView.setRightPane(gameFacade.getPlayerPane(PlayerEnum.PLAYER_TWO));
    }

    @Override
    public void update(GameEventEnum event) {
        switch (event) {
            case START_GAME, RESET_GAME:
                setPlayerPanes();
                break;
        }
    }
}