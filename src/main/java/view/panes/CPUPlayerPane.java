package view.panes;

import controller.CPUPlayerPaneController;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class CPUPlayerPane extends PlayerPane {
    CPUPlayerPaneController cpuPlayerPaneController;
    Label titleLabel;
    Label subTitleLabel;

    public CPUPlayerPane() {
        titleLabel = new Label();
        titleLabel.setFont(new Font("Arial Black", 24));

        subTitleLabel = new Label();
        subTitleLabel.setFont(new Font("Arial Black", 16));

        getChildren().addAll(titleLabel, subTitleLabel);
    }

    public void setCpuPlayerPaneController(CPUPlayerPaneController cpuPlayerPaneController) {
        this.cpuPlayerPaneController = cpuPlayerPaneController;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getSubTitleLabel() {
        return subTitleLabel;
    }
}
