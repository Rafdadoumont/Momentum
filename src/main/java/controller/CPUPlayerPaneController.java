package controller;

import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import model.PlayerEnum;
import model.player.PlayerTypeEnum;
import view.panes.CPUPlayerPane;

public class CPUPlayerPaneController extends PlayerPaneController implements Observer {
    CPUPlayerPane cpuPlayerPane;
    PlayerTypeEnum playerType;

    public CPUPlayerPaneController(CPUPlayerPane cpuPlayerPane, GameFacade gameFacade, PlayerEnum player, PlayerTypeEnum playerType) {
        super(cpuPlayerPane, gameFacade, player);
        this.cpuPlayerPane = cpuPlayerPane;
        this.playerType = playerType;
        super.updateTurnIndicator();

        cpuPlayerPane.getTitleLabel().setText(super.player.toString());
        cpuPlayerPane.getTitleLabel().setTextFill(super.player.getColor());
        cpuPlayerPane.getSubTitleLabel().setText(playerType.toString().charAt(0) + playerType.toString().substring(1).toLowerCase());
        cpuPlayerPane.getSubTitleLabel().setTextFill(super.player.getColor());
    }

//    private void updateMarbleCount() {
//        if (super.player == PlayerEnum.PLAYER_ONE) {
//            cpuPlayerPane.getMarbleCountLabel().setText("Marbles: " + gameFacade.getPlayerOneMarbleCount());
//        } else if (super.player == PlayerEnum.PLAYER_TWO) {
//            cpuPlayerPane.getMarbleCountLabel().setText("Marbles: " + gameFacade.getPlayerTwoMarbleCount());
//        }
//    }

    @Override
    public void update(GameEventEnum event) {
        super.update(event);

//        switch (event) {
//            case START_GAME, MOVE_PLAYED -> updateMarbleCount();
//        }
    }
}
