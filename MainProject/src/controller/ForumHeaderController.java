package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ForumHeaderController {

    @FXML
    private Label labelHeader;

    public void setData(String h)
    {
        labelHeader.setText(h);
    }

}
