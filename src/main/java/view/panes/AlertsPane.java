package view.panes;

import controller.AlertsPaneController;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDateTime;

import static javafx.scene.layout.VBox.setVgrow;

public class AlertsPane extends BorderPane {
    AlertsPaneController alertsPaneController;
    VBox alertsVBox;
    ScrollPane scrollPane;

    public AlertsPane() {
        alertsVBox = new VBox();
        scrollPane = new ScrollPane(alertsVBox);
        scrollPane.setMinWidth(400);
        scrollPane.setMinHeight(100);

        setCenter(scrollPane);
    }

    public void setAlertsPaneController(AlertsPaneController alertsPaneController) {
        this.alertsPaneController = alertsPaneController;
    }

    public void addAlert(String alert, boolean isError) {
        String alertText = timeString() + "   " + alert;
        Label alertLabel = new Label(alertText);
        alertLabel.setWrapText(true);
        if (isError) alertLabel.setTextFill(Color.RED);
        alertsVBox.getChildren().add(0, alertLabel);
    }


    private String timeString() {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
    }
}
