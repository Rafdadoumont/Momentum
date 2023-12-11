package controller;

import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import model.*;
import model.player.PlayerTypeEnum;
import view.panes.SelectionPlayerPane;

public class SelectionPlayerPaneController extends PlayerPaneController implements Observer {
    SelectionPlayerPane selectionPlayerPane;
    GameFacade gameFacade;

    public SelectionPlayerPaneController(SelectionPlayerPane selectionPlayerPane, GameFacade gameFacade, PlayerEnum player) {
        super(selectionPlayerPane, gameFacade, player);
        this.selectionPlayerPane = selectionPlayerPane;
        this.gameFacade = gameFacade;

        selectionPlayerPane.getTitle().setText("Select " + player);
        selectionPlayerPane.getTitle().setFont(new Font("Arial Black", 24));
        selectionPlayerPane.getTitle().setTextFill(super.player.getColor());

        for (PlayerTypeEnum playerTypeEnum : PlayerTypeEnum.values()) {
            ComboBox<PlayerTypeEnum> comboBox = selectionPlayerPane.getPlayerComboBox();
            comboBox.getItems().add(playerTypeEnum);
            comboBox.setOnAction(event -> {
                if (super.player == PlayerEnum.PLAYER_ONE) {
                    gameFacade.setPlayerOne(comboBox.getValue());
                } else if (super.player == PlayerEnum.PLAYER_TWO) {
                    gameFacade.setPlayerTwo(comboBox.getValue());
                }
            });
        }
    }

    @Override
    public void update(GameEventEnum event) {
        super.update(event);
    }
}
