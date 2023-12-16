package model.player;

import view.panes.PlayerPaneEnum;

public enum PlayerTypeEnum {
    HUMAN("model.player.HumanPlayer", "controller.HumanBoardController", PlayerPaneEnum.HUMAN),
    RANDOM("model.player.RandomPlayer", "controller.CPUBoardController", PlayerPaneEnum.CPU),
    MINIMAX("model.player.MinimaxPlayer", "controller.CPUBoardController", PlayerPaneEnum.CPU);

    private final String className;
    private final String controllerClassName;
    private final PlayerPaneEnum playerPane;

    PlayerTypeEnum(String className, String controllerClassName, PlayerPaneEnum playerPane) {
        this.className = className;
        this.controllerClassName = controllerClassName;
        this.playerPane = playerPane;
    }

    public String getClassName() {
        return className;
    }

    public String getControllerClassName() {
        return controllerClassName;
    }

    public PlayerPaneEnum getPlayerPane() {
        return playerPane;
    }
}
