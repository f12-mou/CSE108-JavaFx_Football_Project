package controller;

import data.Players;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ResultController {

    @FXML
    private VBox ContainerVbox;

    @FXML
    private Label dateLabel;

    public static Stage getStageModify() {
        return stageModify;
    }

    private static ModifyController controllerM;

    private static   Stage stageModify;
    private List<String>details;
    private List<String>names;
    private Button[]button;
    private Hyperlink[]sell;

    public static ModifyController getControllerM() {
        return controllerM;
    }

    public void setData(List<String>details, List<String>names)
    {
        this.details=details;
        this.names=names;
    }

public void setData3(List<Players>list){
    button=new Button[list.size()];
    sell=new Hyperlink[list.size()];
    HBox h[]=new HBox[list.size()];
        List<String> dates=QueriesController.getTotal_times();
        dateLabel.setText(dates.get(dates.size()-1));
    AnchorPane anchorPane=null;
        for(int i=0;i<details.size();i++)
        {
            FXMLLoader loader2=new FXMLLoader();
            loader2.setLocation(getClass().getResource("/sample/Question.fxml"));
            try {
                     anchorPane=(AnchorPane) loader2.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            QuestionController questionController=loader2.getController();
            questionController.setData(details.get(i),names.get(i));
            ContainerVbox.getChildren().add(anchorPane);
        }

        for(int i=0;i<list.size();i++)
        {
            button[i]=new Button("Change");
            sell[i]=new Hyperlink("Sell this Player");
            h[i]=new HBox();
            h[i].setStyle("-fx-spacing: 60;-fx-pref-width: 200;-fx-background-color:black;-fx-background-radius:30;-fx-alignment:Center;");

            FXMLLoader loader2=new FXMLLoader();
            Players p=new Players();
            p=list.get(i);
            loader2.setLocation(getClass().getResource("/sample/ResultP.fxml"));
            try {
                anchorPane=(AnchorPane) loader2.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
           ResultPController resultPController=loader2.getController();
            resultPController.setData(p,p.GetName(),p.GetCountry(),p.GetPosition(),p.GetNumber(),p.GetAge(),p.GetHeight(),p.GetSalary());

            ContainerVbox.getChildren().add(anchorPane);
            h[i].getChildren().addAll(button[i],sell[i]);
           ContainerVbox.getChildren().add(h[i]);
        }
    for(int i=0;i<list.size();i++)
    {
        final int ii=i;
        button[ii].setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/ModifyPlayers.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
             controllerM=loader.getController();
            controllerM.Modify(list.get(ii));
            Scene scene=new Scene(root,800,600);
            stageModify=new Stage();
            FileInputStream input = null;
            try {
                input = new FileInputStream("TextureBlue.jpg");
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
            controllerM.getMainRoot().setBackground(background);
            stageModify.setScene(scene);
            stageModify.show();
        });
    }
    for(int i=0;i<list.size();i++)
    {
        final int ii=i;
        sell[ii].setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/SellPlayer.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SellController controller=loader.getController();
            controller.Modify(list.get(ii));
            Scene scene=new Scene(root,800,550);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        });
    }
    }
}

