package view.panes;

import controller.PlayerPaneController;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public abstract class PlayerPane extends VBox {
    PlayerPaneController playerPaneController;

    public PlayerPane() {
        setPadding(new Insets(10));
        setMinWidth(200);
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    public void setPlayerPaneController(PlayerPaneController playerPaneController) {
        this.playerPaneController = playerPaneController;
    }
}
