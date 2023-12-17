package model.player;

import controller.CPUBoardController;
import controller.CPUPlayerPaneController;
import controller.HumanBoardController;
import controller.PlayerPaneController;
import model.Game;
import model.GameEventEnum;
import model.GameFacade;
import model.PlayerEnum;
import view.panes.BoardGridPane;
import view.panes.PlayerPaneFactory;

import java.lang.reflect.Constructor;

public class PlayerFactory {
    GameFacade gameFacade;

    public PlayerFactory(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public Player createPlayer(PlayerTypeEnum playerType, Game game) {
        Player out = null;

        try {
            Class<?> playerClass = Class.forName(playerType.getClassName());
            Constructor<?> playerConstructor = playerClass.getConstructor(Game.class);
            out = (Player) playerConstructor.newInstance(game);

            Class<?> boardControllerClass = Class.forName(playerType.getControllerClassName());
            Constructor<?> controllerConstructor = boardControllerClass.getConstructor(Player.class, BoardGridPane.class);
            Object controller = controllerConstructor.newInstance(out, gameFacade.getBoardGridPane());

            if (out instanceof CPUPlayer cpuPlayer) {
                CPUBoardController cpuBoardController = (CPUBoardController) controller;
                cpuPlayer.setCpuBoardController(cpuBoardController);

                gameFacade.addObserver(cpuBoardController, GameEventEnum.START_GAME);
                gameFacade.addObserver(cpuBoardController, GameEventEnum.MOVE_PLAYED);
            } else {
                HumanBoardController humanBoardController = (HumanBoardController) controller;
                HumanPlayer humanPlayer = (HumanPlayer) out;
                humanPlayer.setHumanBoardController(humanBoardController);
                gameFacade.addObserver(humanBoardController, GameEventEnum.START_GAME);
                gameFacade.addObserver(humanBoardController, GameEventEnum.MOVE_PLAYED);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return out;
    }
}
