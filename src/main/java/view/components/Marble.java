package view.components;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.PlayerEnum;

public class Marble extends Circle {
    private static final double RADIUS = 25;

    public Marble() {
        super();
        initializeMarble(Color.BLACK);
    }

    public Marble(PlayerEnum player) {
        super();
        initializeMarble(player.getColor());
    }

    private void initializeMarble(Color color) {
        setRadius(RADIUS);
        setFill(color);
        GridPane.setHalignment(this, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(this, javafx.geometry.VPos.CENTER);
    }
}
