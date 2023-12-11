package view.panes;

import controller.HumanPlayerPaneController;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HumanPlayerPane extends PlayerPane {
    HumanPlayerPaneController humanStatsPaneController;
    Label titleLabel;
    Label subTitleLabel;
    Label marbleCountLabel;

    public HumanPlayerPane() {
        titleLabel = new Label();
        titleLabel.setFont(new Font("Arial Black", 24));

        subTitleLabel = new Label();
        subTitleLabel.setFont(new Font("Arial Black", 16));

        marbleCountLabel = new Label();

        getChildren().addAll(titleLabel, subTitleLabel, marbleCountLabel);
    }

    public void setHumanStatsPaneController(HumanPlayerPaneController humanStatsPaneController) {
        this.humanStatsPaneController = humanStatsPaneController;
        super.setPlayerPaneController(humanStatsPaneController);
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getSubTitleLabel() {
        return subTitleLabel;
    }

    public Label getMarbleCountLabel() {
        return marbleCountLabel;
    }
}
