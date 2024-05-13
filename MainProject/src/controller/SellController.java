package controller;

import data.Players;
import dto.SendPlayer;
import dto.ToClientPriceList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SellController {
    private Players players;
    @FXML
    private Label name;

    @FXML
    private Label country;

    @FXML
    private Label position;

    @FXML
    private Label salary;

    @FXML
    private Label age;

    @FXML
    private Label height;

    @FXML
    private Label number;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private PieChart piechart;
    @FXML
    private TextField auction;

    @FXML
    private TextArea comment;
    @FXML
    private Button button;

    public void Modify(Players players) {
        this.players = players;
        name.setText(players.GetName());
        country.setText(players.GetCountry());
        position.setText(players.GetPosition());
        age.setText(String.valueOf(players.GetAge()));
        height.setText(String.valueOf(players.GetHeight()));
        number.setText(String.valueOf(players.GetNumber()));
        salary.setText(String.valueOf(players.GetSalary()));
    }

    public void buttonAction(javafx.event.ActionEvent event) {
        String comment = this.comment.getText();
        String taka = this.auction.getText();
        SendPlayer sendPlayer = new SendPlayer();
        Players p = sendPlayer.getPlayer();
        p.SetName(players.GetName());
        p.SetCountry(players.GetCountry());
        p.SetPosition(players.GetPosition());
        p.SetClub(players.GetClub());
        p.SetSalary(players.GetSalary());
        p.SetNumber(players.GetNumber());
        p.SetHeight(players.GetHeight());
        p.SetAge(players.GetAge());
        sendPlayer.setTaka(taka);
        sendPlayer.setClientName(Main.getClientName());
        sendPlayer.setName(comment);
        try {
            Main.getClient().getNetworkUtil().write(sendPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hyperlinkAction(ActionEvent event) {
        try {
            Main.getClient().getNetworkUtil().write("sendPricelist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> list = new ArrayList<>();
        Object o = new Object();
        try {
            o = Main.getClient().getNetworkUtil().read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (o instanceof ToClientPriceList) {
            ToClientPriceList toClientPriceList = (ToClientPriceList) o;
            list = toClientPriceList.getPriceList();
        }
        if (!(list.size() == 0)) {
            Double max = 0.0;
            Double min = 90000000.0;
            for (int i = 0; i < list.size(); i++) {
                Double d = list.get(i);
                if (d >= max)
                    max = d;
                if (d <= min)
                    min = d;
            }
            PieChart.Data data[] = new PieChart.Data[(max.intValue() - min.intValue() + 1) / 1000];
            int array[] = new int[(max.intValue() - min.intValue() + 1) / 1000];
            for (int i = 0; i < array.length; i++)
                array[i] = 0;
            for (int i = 0; i < list.size(); i++) {
                Double d = list.get(i);
                int temp = min.intValue();
                for (int j = 0; j < array.length; j++) {
                    if (d <= (temp + 1000) && d >= temp) {
                        array[j] += 1;
                        break;
                    } else
                        temp += 1000;
                }
            }
            for (int i = 0; i < array.length; i++) {
                data[i] = new PieChart.Data(String.valueOf(min) + " - " + String.valueOf(max), array[i]);
                min += 1000;
            }
            ObservableList<PieChart.Data> list2 = FXCollections.observableArrayList(data);
            piechart.setData(list2);

        } else {
            System.out.println("prb in loading piechart");
        }
    }
}
