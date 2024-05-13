package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RegisterController {
    @FXML
    private Button done;

    public Label getRegisterLabel() {
        return registerLabel;
    }

    @FXML
    private Label registerLabel;

    public AnchorPane getAnchorpane() {
        return anchorpane;
    }

    @FXML
    private AnchorPane anchorpane;
    @FXML
    public void doneAction(ActionEvent event)
    {
        LoginController.getSecondaryStage().close();
    }


}
