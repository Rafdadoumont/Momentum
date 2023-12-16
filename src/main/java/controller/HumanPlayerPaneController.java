package controller;

import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import model.PlayerEnum;
import model.player.PlayerTypeEnum;
import view.panes.HumanPlayerPane;

public class HumanPlayerPaneController extends PlayerPaneController implements Observer {
    HumanPlayerPane humanPlayerPane;
    PlayerTypeEnum playerType;

    public HumanPlayerPaneController(HumanPlayerPane humanPlayerPane, GameFacade gameFacade, PlayerEnum player, PlayerTypeEnum playerType) {
        super(humanPlayerPane, gameFacade, player);
        this.humanPlayerPane = humanPlayerPane;
        this.playerType = playerType;
        super.updateTurnIndicator();

        humanPlayerPane.getTitleLabel().setText(super.player.toString());
        humanPlayerPane.getTitleLabel().setTextFill(super.player.getColor());
        humanPlayerPane.getSubTitleLabel().setText(playerType.toString().charAt(0) + playerType.toString().substring(1).toLowerCase());
        humanPlayerPane.getSubTitleLabel().setTextFill(super.player.getColor());
        updateMarbleCount();
    }

    private void updateMarbleCount() {
        if (super.player == PlayerEnum.PLAYER_ONE) {
            humanPlayerPane.getMarbleCountLabel().setText("Marbles: " + gameFacade.getPlayerOneMarbleCount());
        } else if (super.player == PlayerEnum.PLAYER_TWO) {
            humanPlayerPane.getMarbleCountLabel().setText("Marbles: " + gameFacade.getPlayerTwoMarbleCount());
        }
    }

    @Override
    public void update(GameEventEnum event) {
        super.update(event);
        switch (event) {
            case START_GAME, MOVE_PLAYED -> updateMarbleCount();
        }
    }
}
