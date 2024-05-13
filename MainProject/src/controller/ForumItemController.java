package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ForumItemController {

    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Label field;

    @FXML
    private Label prev;

    @FXML
    private Label new_details;
    public void setData(String name,String number,String field,String prev,String n)
    {
        this.name.setText(name);
        this.number.setText(number);
        this.field.setText(field);
        this.prev.setText(prev);
        this.new_details.setText(n);
    }

}
