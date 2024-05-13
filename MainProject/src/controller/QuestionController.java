package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuestionController {

    @FXML
    private Label qsLabel;

    @FXML
    private Label ansLabel;
    public void setData(String qs,String ans)
    {
        qsLabel.setText(ans);
        ansLabel.setText(qs);
    }

}
