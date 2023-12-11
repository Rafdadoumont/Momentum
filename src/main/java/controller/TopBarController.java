package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;
import model.GameEventEnum;
import model.GameFacade;
import model.Observer;
import model.exceptions.StateException;
import model.state.*;
import view.TopBar;

public class TopBarController implements Observer {
    TopBar topBar;
    GameFacade gameFacade;
    Timeline timeline;

    public TopBarController(TopBar topBar, GameFacade gameFacade) {
        this.topBar = topBar;
        this.gameFacade = gameFacade;

        topBar.getGameActionButton().setOnMouseClicked(e -> handleGameActionButton());
    }

    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            int timeSeconds = 0;
            @Override
            public void handle(ActionEvent event) {
                timeSeconds++;
                String timeString = String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60);
                topBar.getTimerLabel().setText(timeString);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopTimer() {
        timeline.stop();
    }

    private void resetTimer() {
        topBar.getTimerLabel().setText("00:00");
    }

    private void updateGameActionButton() {
        Button button = topBar.getGameActionButton();
        StateContext context = gameFacade.getContext();
        GameState state = context.getState();

        if (state instanceof IdleState) {
            button.setText("Start Game");
        } else if (state instanceof RunningState) {
            button.setText("End Game");
        } else if (state instanceof FinishedState) {
            button.setText("Reset Game");
        }
    }

    private void handleGameActionButton() {
        StateContext context = gameFacade.getContext();
        GameState state = context.getState();

        if (state instanceof IdleState) {
            gameFacade.startGame();
        } else if (state instanceof RunningState) {
            gameFacade.finishGameEarly();
        } else if (state instanceof FinishedState) {
            gameFacade.resetGame();
        }
    }

    @Override
    public void update(GameEventEnum event) {
        switch (event) {
            case START_GAME:
                updateGameActionButton();
                startTimer();
                break;
            case FINISH_GAME:
                updateGameActionButton();
                stopTimer();
                break;
            case RESET_GAME:
                updateGameActionButton();
                resetTimer();
                break;
        }
    }
}
