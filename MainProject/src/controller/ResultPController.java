package controller;

import data.Players;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ResultPController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label PositionLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label salaryLabel;
    @FXML
    private ImageView imageView;
    public void setData(Players p,String getName, String getCountry, String getPosition, int getNumber, int getAge, double getHeight, double getSalary) {
        nameLabel.setText(getName);
         countryLabel.setText("Country: "+getCountry);
        PositionLabel.setText("Position: "+getPosition);
        numberLabel.setText("Number: "+String.valueOf(getNumber));
        ageLabel.setText("Age: "+String.valueOf(getAge));
        heightLabel.setText("Height: "+String.valueOf(getHeight));
        salaryLabel.setText("Salary: "+String.valueOf(getSalary));
        imageView.setImage(p.getImage());
    }
}
