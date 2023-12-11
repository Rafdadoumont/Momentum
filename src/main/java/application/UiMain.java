package application;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameEventEnum;
import model.GameFacade;
import model.PlayerEnum;
import view.GameView;
import view.TopBar;
import view.panes.AlertsPane;
import view.panes.BoardGridPane;
import view.panes.SelectionPlayerPane;

public class UiMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Facade
        GameFacade gameFacade = GameFacade.getInstance();

        // View
        GameView gameView = new GameView();

        // Top Bar
        TopBar topBar = new TopBar();

        // Board
        BoardGridPane boardGridPane = new BoardGridPane();

        // Panes
        SelectionPlayerPane playerOneSelectionPane = new SelectionPlayerPane();
        SelectionPlayerPane playerTwoSelectionPane = new SelectionPlayerPane();
        AlertsPane alertsPane = new AlertsPane();

        // Controllers
        GameViewController gameViewController = new GameViewController(gameView, gameFacade);
        gameView.setGameController(gameViewController);

        TopBarController topBarController = new TopBarController(topBar, gameFacade);
        topBar.setTopBarController(topBarController);

        BoardGridPaneController boardGridPaneController = new BoardGridPaneController(boardGridPane, gameFacade);
        boardGridPane.setBoardGridController(boardGridPaneController);

        SelectionPlayerPaneController playerOneSelectionPaneController = new SelectionPlayerPaneController(playerOneSelectionPane, gameFacade, PlayerEnum.PLAYER_ONE);
        playerOneSelectionPane.setPlayerSelectionPaneController(playerOneSelectionPaneController);

        SelectionPlayerPaneController playerTwoSelectionPaneController = new SelectionPlayerPaneController(playerTwoSelectionPane, gameFacade, PlayerEnum.PLAYER_TWO);
        playerTwoSelectionPane.setPlayerSelectionPaneController(playerTwoSelectionPaneController);

        AlertsPaneController alertsPaneController = new AlertsPaneController(alertsPane, gameFacade);
        alertsPane.setAlertsPaneController(alertsPaneController);

        // View Panes
        gameView.setTopBar(topBar);
        gameView.setLeftPane(playerOneSelectionPane);
        gameView.setCenterPane(boardGridPane);
        gameView.setRightPane(playerTwoSelectionPane);
        gameView.setBottomPane(alertsPane);

        // Observers
        gameFacade.addObserver(gameViewController, GameEventEnum.START_GAME);
        gameFacade.addObserver(gameViewController, GameEventEnum.RESET_GAME);

        gameFacade.addObserver(topBarController, GameEventEnum.START_GAME);
        gameFacade.addObserver(topBarController, GameEventEnum.FINISH_GAME);
        gameFacade.addObserver(topBarController, GameEventEnum.RESET_GAME);
        gameFacade.addObserver(topBarController, GameEventEnum.MOVE_PLAYED);

        gameFacade.addObserver(boardGridPaneController, GameEventEnum.MOVE_PLAYED);
        gameFacade.addObserver(boardGridPaneController, GameEventEnum.RESET_GAME);

        gameFacade.addObserver(alertsPaneController, GameEventEnum.START_GAME);
        gameFacade.addObserver(alertsPaneController, GameEventEnum.FINISH_GAME);
        gameFacade.addObserver(alertsPaneController, GameEventEnum.RESET_GAME);
        gameFacade.addObserver(alertsPaneController, GameEventEnum.EXCEPTION_THROWN);
    }
}
