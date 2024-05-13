package controller;

import data.Main2;
import data.Players;
import dto.BuyPlayers;
import dto.ToClientPlayerList;
import dto.ToClientPriceList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;
import sample.MyListener;
import util.NetworkUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label greetings;
    @FXML
    private TextField searchField;

    @FXML
    private Button search;

    @FXML
    private Label countryLabel;

    @FXML
    private Label clubLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label salaryLabel;

    @FXML
    private Label numberLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox vbox;
    @FXML
    private Button back;
    @FXML
    private Button cartButton;
    private Main main;
    private static ErrorController errorController;
    private static Stage errorStage=new Stage();

    public static Stage getErrorStage() {
        return errorStage;
    }


    public static List<Players> getP() {
        return p;
    }

    public static void setP(List<Players> p) {
        MarketController.p = p;
    }

    private static List<Players> p=new ArrayList<>();
    private List<Double>d=new ArrayList<>();
    private MyListener myListener;
    public static Players chosenPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileInputStream input = null;
        try {
            input = new FileInputStream("BackHome.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView iii=new ImageView(image);
        iii.setFitHeight(32);
        iii.setFitWidth(32);
        back.setGraphic(iii);



        Object o = new Object();
        NetworkUtil networkUtil=Main.getClient().getNetworkUtil();
        try {
            networkUtil.write("sendPlayersList");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
             o=networkUtil.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(o instanceof ToClientPlayerList)
        {
            ToClientPlayerList toClientPlayerList=(ToClientPlayerList) o;
            p=toClientPlayerList.getPlayersList();
        }
        try {
            networkUtil.write("sendPriceList");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            o=networkUtil.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(o instanceof ToClientPriceList)
        {
            ToClientPriceList toClientPriceList=(ToClientPriceList)o;
            d=((ToClientPriceList) o).getPriceList();
        }
        if(p.size()>0)
        {
            setChoosenPlayer(p.get(0));
            myListener=new MyListener() {
                @Override
                public void onClickListener(Players players) {
                    setChoosenPlayer(players);
                }
            };
        }
        try {
            for (int i = 0; i < p.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Item.fxml"));
                AnchorPane anchorPane = loader.load();
                ItemController itemController=loader.getController();
                itemController.setData(p.get(i),d.get(i),myListener);
                vbox.getChildren().add(anchorPane);
            }
        }catch (IOException e){
            System.out.println("detected exception in controller.itemController.java");
        }
    }
    public void setMain(Main main) {
        this.main=main;
    }
    private void setChoosenPlayer(Players players)
        {
            chosenPlayer=players;
        nameLabel.setText(players.GetName());
        countryLabel.setText(players.GetCountry());
        clubLabel.setText(players.GetClub());
        positionLabel.setText(players.GetPosition());
        ageLabel.setText(String.valueOf(players.GetAge()));
        numberLabel.setText(String.valueOf(players.GetNumber()));
        heightLabel.setText(String.valueOf(players.GetHeight()));
        salaryLabel.setText(String.valueOf(players.GetSalary()));
        greetings.setText("Hi, I'm "+players.GetName());
       for(int i=0;i<p.size();i++)
       {
           if(chosenPlayer.GetName().equalsIgnoreCase(p.get(i).GetName()))
           {
               moneyLabel.setText(String.valueOf(d.get(i)));
               break;
           }
       }
            FileInputStream input = null;
            try {
                input = new FileInputStream("DefaultImage.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(input);
        imageView.setImage(image);
    }
    public void cartAction()
    {
        if(!(chosenPlayer.GetClub().equalsIgnoreCase(Main.getClientName()))) {
            Players p = new Players();
            p.SetName(chosenPlayer.GetName());
            p.SetCountry(chosenPlayer.GetCountry());
            p.SetClub(chosenPlayer.GetClub());
            p.SetPosition(chosenPlayer.GetPosition());
            p.SetAge(chosenPlayer.GetAge());
            p.SetSalary(chosenPlayer.GetSalary());
            p.SetHeight(chosenPlayer.GetHeight());
            p.SetNumber(chosenPlayer.GetNumber());
            BuyPlayers buyPlayers = new BuyPlayers();
            buyPlayers.setName(Main.getClientName());
            buyPlayers.setP(p);
            Main.buyClubs.add(p.GetClub());
            Main.buyNumbers.add(String.valueOf(p.GetNumber()));

            try {
                Main.getClient().getNetworkUtil().write(buyPlayers);
            } catch (IOException e) {
                e.printStackTrace();
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Purchase.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PurchaseController purchaseController=loader.getController();
           purchaseController.getLabel().setText("Congrats!!! Successfully added the Player to your Club.");
            FileInputStream input = null;
            try {
                input = new FileInputStream("Purchase.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(input);
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));
            Background background = new Background(backgroundimage);
           purchaseController.getAnchorPane().setBackground(background);
            Scene scene=new Scene(root,500,360);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        }
        else
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Error.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            errorController=loader.getController();
            errorController.getLabel().setText("Club's own player can't be bought.");
            FileInputStream input = null;
            try {
                input = new FileInputStream("Error.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(input);
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));
            Background background = new Background(backgroundimage);
            errorController.getAnchorPane().setBackground(background);
            Scene scene=new Scene(root,800,500);
            errorStage.setScene(scene);
            errorStage.show();
        }

    }
    public void searchAction()
    {
        String str=searchField.getText();
        for(int i=0;i<p.size();i++)
        {
            if(str.equalsIgnoreCase(p.get(i).GetName()))
            {
                setChoosenPlayer(p.get(i));
            }
        }
    }
    public void backAction(ActionEvent event)
    {
        try {
            main.showHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
