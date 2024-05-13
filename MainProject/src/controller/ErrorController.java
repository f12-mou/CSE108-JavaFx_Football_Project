package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ErrorController{
    @FXML
    private Button button;
    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;



    @FXML
    void buttonAction(ActionEvent event) {

        ModifyController.getErrorStage().close();
        SearchFinal.getErrorStage().close();
        MarketController.getErrorStage().close();
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
}
