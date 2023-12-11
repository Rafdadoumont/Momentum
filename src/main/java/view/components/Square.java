package view.components;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Square extends StackPane {
    byte x;
    byte y;

    public Square(byte x, byte y) {
        super();
        this.x = x;
        this.y = y;
        setMinHeight(60);
        setMinWidth(60);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        setOnMouseEntered(e -> {
            if (getChildren().isEmpty()) {
                MarbleGhost marbleGhost = new MarbleGhost();
                getChildren().add(marbleGhost);
            }
        });

        setOnMouseExited(e -> {
            getChildren().removeIf(node -> node instanceof MarbleGhost);
        });
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public void placeMarble(Marble marble) {
        getChildren().add(marble);
    }

    public void clearMarble() {
        getChildren().clear();
    }
}
