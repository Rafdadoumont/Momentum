package view.panes;

import controller.CPUPlayerPaneController;
import controller.HumanPlayerPaneController;
import controller.PlayerPaneController;
import controller.SelectionPlayerPaneController;
import model.GameEventEnum;
import model.GameFacade;
import model.PlayerEnum;
import model.player.PlayerTypeEnum;

import java.lang.reflect.Constructor;

public class PlayerPaneFactory {

    GameFacade gameFacade;

    public PlayerPaneFactory(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public PlayerPane createPlayerPane(PlayerTypeEnum playerType, PlayerEnum player) {
        try {
            PlayerPaneEnum playerPane = playerType == null ? PlayerPaneEnum.SELECTION : playerType.getPlayerPane();
            Class<?> playerPaneClass = Class.forName(playerPane.getClassName());
            Constructor<?> playerPaneConstructor = playerPaneClass.getConstructor();
            PlayerPane out = (PlayerPane) playerPaneConstructor.newInstance();

            Class<?> playerPaneControllerClass = Class.forName(playerPane.getControllerClassName());
            Constructor<?> playerPaneControllerConstructor;
            PlayerPaneController controller;

            if (playerPaneControllerClass == SelectionPlayerPaneController.class) {
                playerPaneControllerConstructor = playerPaneControllerClass.getConstructor(out.getClass(), GameFacade.class, PlayerEnum.class);
                controller = (PlayerPaneController) playerPaneControllerConstructor.newInstance(out, gameFacade, player);
            } else {
                playerPaneControllerConstructor = playerPaneControllerClass.getConstructor(out.getClass(), GameFacade.class, PlayerEnum.class, PlayerTypeEnum.class);
                controller = (PlayerPaneController) playerPaneControllerConstructor.newInstance(out, gameFacade, player, playerType);
            }
            out.setPlayerPaneController(controller);

            if (out instanceof SelectionPlayerPane selectionPlayerPane) {
                SelectionPlayerPaneController selectionController = (SelectionPlayerPaneController) controller;
                selectionPlayerPane.setPlayerSelectionPaneController(selectionController);
                gameFacade.addObserver(selectionController, GameEventEnum.START_GAME);
            } else if (out instanceof HumanPlayerPane humanPlayerPane) {
                HumanPlayerPaneController humanController = (HumanPlayerPaneController) controller;
                humanPlayerPane.setHumanStatsPaneController(humanController);
                gameFacade.addObserver(humanController, GameEventEnum.MOVE_PLAYED);
                gameFacade.addObserver(humanController, GameEventEnum.FINISH_GAME);
            } else if (out instanceof CPUPlayerPane cpuPlayerPane) {
                CPUPlayerPaneController cpuController = (CPUPlayerPaneController) controller;
                cpuPlayerPane.setCpuPlayerPaneController(cpuController);
                gameFacade.addObserver(cpuController, GameEventEnum.MOVE_PLAYED);
                gameFacade.addObserver(cpuController, GameEventEnum.FINISH_GAME);
            }
            return out;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
