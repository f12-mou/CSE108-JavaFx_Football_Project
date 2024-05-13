package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FieldQueryController {

    @FXML
    private Label fieldName;

    @FXML
    private Label detailsName;
    public void setData(String s,String s1)
    {
        fieldName.setText(s);
        detailsName.setText(s1);
    }

}

