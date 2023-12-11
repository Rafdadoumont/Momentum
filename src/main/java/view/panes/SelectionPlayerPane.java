package view.panes;

import controller.SelectionPlayerPaneController;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.player.PlayerTypeEnum;

public class SelectionPlayerPane extends PlayerPane {
    SelectionPlayerPaneController selectionPlayerPaneController;

    Label title;

    ComboBox<PlayerTypeEnum> playerComboBox;

    public SelectionPlayerPane() {
        setPadding(new Insets(10));
        setMinWidth(200);
        VBox.setVgrow(this, Priority.ALWAYS);

        title = new Label();
        playerComboBox = new ComboBox<>();

        getChildren().addAll(title, playerComboBox);
    }

    public void setPlayerSelectionPaneController(SelectionPlayerPaneController selectionPlayerPaneController) {
        this.selectionPlayerPaneController = selectionPlayerPaneController;
    }

    public Label getTitle() {
        return title;
    }

    public ComboBox<PlayerTypeEnum> getPlayerComboBox() {
        return playerComboBox;
    }
}
