package view.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class Spacer extends Region {
    public Spacer() {
        super();
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}