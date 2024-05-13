package controller;

import data.Players;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.MyListener;

import java.awt.event.MouseEvent;

public class ItemController {

    @FXML
    private AnchorPane anchorpane;
    private Players players;

    @FXML
    private Label nameLabel;

    @FXML
    private Label takaLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label ClubLabel;

    @FXML
    private Label PositionLabel;
    private MyListener myListener;

    public void setData(Players players,Double d,MyListener myListener) {
        this.myListener=myListener;
        this.players=players;
        nameLabel.setText(players.GetName());
        countryLabel.setText(players.GetCountry());
        ClubLabel.setText(players.GetClub());
        PositionLabel.setText(players.GetPosition());
        takaLabel.setText(String.valueOf(d)+" $");
    }
    @FXML
    public void click(javafx.scene.input.MouseEvent mouseEvent) {
        myListener.onClickListener(players);
    }
}
