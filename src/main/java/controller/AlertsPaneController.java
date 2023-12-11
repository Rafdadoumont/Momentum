package controller;

import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import model.state.*;
import view.panes.AlertsPane;

public class AlertsPaneController implements Observer {
    AlertsPane alertsPane;
    GameFacade gameFacade;

    public AlertsPaneController(AlertsPane alertsPane, GameFacade gameFacade) {
        this.alertsPane = alertsPane;
        this.gameFacade = gameFacade;
        addStateAlert();
    }

    private void addStateAlert() {
        StateContext context = gameFacade.getContext();
        GameState state = context.getState();

        if (state instanceof IdleState) {
            alertsPane.addAlert("Waiting for game to start...", false);
        } else if (state instanceof RunningState) {
            alertsPane.addAlert("Game is running", false);
        } else if (state instanceof FinishedEarlyState) {
            alertsPane.addAlert("Game finished early", false);
        } else if (state instanceof FinishedState) {
            alertsPane.addAlert("Game is finished, " + gameFacade.getWonPlayer() + " won!", false);
        }
    }

    public void updateErrorAlerts() {
        alertsPane.addAlert(gameFacade.getException().getMessage(), true);
    }

    @Override
    public void update(GameEventEnum event) {
        switch (event) {
            case START_GAME, FINISH_GAME, RESET_GAME:
                addStateAlert();
                break;
            case EXCEPTION_THROWN:
                updateErrorAlerts();
                break;
        }
    }
}
