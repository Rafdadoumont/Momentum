package view;

import controller.TopBarController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import view.components.Spacer;

public class TopBar extends ToolBar {
    TopBarController topBarController;
    Label timerLabel;
    Button gameActionButton;

    public TopBar() {
        super();
        setPadding(new Insets(10));
        HBox.setHgrow(this, Priority.ALWAYS);

        timerLabel = new Label("00:00");
        gameActionButton = new Button("Start Game");
        getItems().addAll(timerLabel, new Spacer(), gameActionButton);
    }

    public void setTopBarController(TopBarController topBarController) {
        this.topBarController = topBarController;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public Button getGameActionButton() {
        return gameActionButton;
    }
}
