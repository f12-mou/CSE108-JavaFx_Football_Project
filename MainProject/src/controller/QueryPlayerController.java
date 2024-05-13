package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QueryPlayerController {

    @FXML
    private Label name;

    @FXML
    private Label country;

    @FXML
    private Label club;

    @FXML
    private Label position;

    @FXML
    private Label age;

    @FXML
    private Label height;

    @FXML
    private Label salary;

    @FXML
    private Label number;
    public void setData(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){
        name.setText(s1);
        country.setText(s2);
        club.setText(s3);
        position.setText(s4);
        age.setText(s5);
        height.setText(s6);
        salary.setText(s7);
        number.setText(s8);
    }

}
