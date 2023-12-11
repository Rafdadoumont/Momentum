package model;

import model.exceptions.StateException;
import model.player.Player;
import model.player.PlayerTypeEnum;
import model.player.PlayerFactory;
import model.state.StateContext;
import view.panes.BoardGridPane;
import view.panes.PlayerPane;
import view.panes.PlayerPaneFactory;

public class GameFacade {
    private static GameFacade gameFacade;
    private final Game game;
    private final PlayerPaneFactory playerPaneFactory;
    private final PlayerFactory playerFactory;
    private BoardGridPane boardGridPane;

    private GameFacade() {
        this.game = new Game();
        playerPaneFactory = new PlayerPaneFactory(this);
        playerFactory = new PlayerFactory(this);
    }

    public static GameFacade getInstance() {
        if (gameFacade == null) {
            gameFacade = new GameFacade();
        }
        return gameFacade;
    }

    public byte[][] getBoard() {
        return game.getBoard();
    }

    public BoardGridPane getBoardGridPane() {
        return boardGridPane;
    }

    public StateContext getContext() {
        return game.getContext();
    }

    public int getRound() {
        return game.getRound();
    }

    public void setBoardGridPane(BoardGridPane boardGridPane) {
        this.boardGridPane = boardGridPane;
    }

    public void setPlayerOne(PlayerTypeEnum playerType) {
        Player player = playerFactory.createPlayer(playerType);
        game.setPlayerOne(player);
    }

    public void setPlayerTwo(PlayerTypeEnum playerType) {
        Player player = playerFactory.createPlayer(playerType);
        game.setPlayerTwo(player);
    }

    public void startGame() {
        game.start();
    }

    public void finishGameEarly() {
        game.finishEarly();
    }

    public void resetGame() {
        game.reset();
    }

    public PlayerPane getPlayerPane(PlayerEnum playerConfig) {
        Player player = null;

        if (playerConfig == PlayerEnum.PLAYER_ONE) {
            player = game.getPlayerOne();
        } else if (playerConfig == PlayerEnum.PLAYER_TWO) {
            player = game.getPlayerTwo();
        }

        if (player != null) {
            PlayerTypeEnum playerType = player.getPlayerType();
            return playerPaneFactory.createPlayerPane(playerType, playerConfig);
        } else {
            return playerPaneFactory.createPlayerPane(null, playerConfig);
        }
    }

    public int getPlayerOneMarbleCount() {
        return game.getPlayerOneMarbleCount();
    }

    public int getPlayerTwoMarbleCount() {
        return game.getPlayerTwoMarbleCount();
    }

    public PlayerEnum getWonPlayer() {
        return game.getWonPlayer();
    }

    public Exception getException() {
        return game.getException();
    }

    public void addObserver(Observer observer, GameEventEnum event) {
        game.addObserver(observer, event);
    }
}
