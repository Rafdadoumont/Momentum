package view;

import controller.GameViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameView {
    GameViewController gameViewController;
    Scene scene;
    HBox topHBox;
    VBox leftVBox;
    VBox centerVBox;
    VBox rightVBox;
    HBox bottomHBox;

    public GameView() {
        Screen screen = Screen.getPrimary();
        Stage stage = new Stage();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setTitle("Momentum");

        // Root layout
        VBox rootVBox = new VBox();

        // Top
        topHBox = new HBox();

        // Split pane
        HBox centerHBox = new HBox();
        centerHBox.setPadding(new Insets(20));

        // Left
        leftVBox = new VBox();

        // Center
        centerVBox = new VBox();
        HBox.setHgrow(centerVBox, Priority.ALWAYS);
        centerVBox.setAlignment(Pos.CENTER);

        // Right
        rightVBox = new VBox();

        // Bottom
        bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setPadding(new Insets(10));

        rootVBox.getChildren().add(topHBox);
        centerHBox.getChildren().addAll(leftVBox, centerVBox, rightVBox);
        rootVBox.getChildren().add(centerHBox);
        rootVBox.getChildren().add(bottomHBox);

        scene = new Scene(rootVBox, bounds.getWidth() / 1.5, bounds.getHeight() / 1.3);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void setGameController(GameViewController gameViewController) {
        this.gameViewController = gameViewController;
    }

    public void setTopBar(TopBar topBar) {
        topHBox.getChildren().clear();
        topHBox.getChildren().add(topBar);
    }

    public void setLeftPane(Pane pane) {
        leftVBox.getChildren().clear();
        leftVBox.getChildren().add(pane);
    }

    public void setCenterPane(Pane pane) {
        centerVBox.getChildren().clear();
        centerVBox.getChildren().add(pane);
    }

    public void setRightPane(Pane pane) {
        rightVBox.getChildren().clear();
        rightVBox.getChildren().add(pane);
    }

    public void setBottomPane(Pane pane) {
        bottomHBox.getChildren().clear();
        bottomHBox.getChildren().add(pane);
    }
}
