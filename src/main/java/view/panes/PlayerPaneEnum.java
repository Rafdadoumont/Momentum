package view.panes;

public enum PlayerPaneEnum {
    SELECTION("view.panes.SelectionPlayerPane", "controller.SelectionPlayerPaneController"),
    HUMAN("view.panes.HumanPlayerPane", "controller.HumanPlayerPaneController"),
    CPU("view.panes.CPUPlayerPane", "controller.CPUPlayerPaneController");

    private final String className;
    private final String controllerClassName;

    PlayerPaneEnum(String className, String controllerClassName) {
        this.className = className;
        this.controllerClassName = controllerClassName;
    }

    public String getClassName() {
        return className;
    }

    public String getControllerClassName() {
        return controllerClassName;
    }
}
