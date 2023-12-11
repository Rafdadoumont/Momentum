package view.components;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public class MarbleGhost extends Marble {
    public MarbleGhost() {
        super();
        setFill(Color.GRAY);
        setOpacity(0.5);

        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
        });
    }
}
