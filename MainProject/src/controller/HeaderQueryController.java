package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderQueryController {

    @FXML
    private Label header1;

    @FXML
    private Label header2;

    @FXML
    private Label header3;
    public void setData(String s,String s1,String s2)
    {
        header1.setText(s);
        header2.setText(s1);
        header3.setText(s2);
    }

}
