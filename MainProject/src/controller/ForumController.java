package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ForumController implements Initializable {
    private Main main;
    @FXML
    private Button home;

    @FXML
    private Button statistics;

    @FXML
    private Button search;

    @FXML
    private Button queries;

    @FXML
    private Button logout;

    @FXML
    private VBox vbox;
    private static List<String> names;
    private static List<String> fields;
    private static List<String> prevDetails;
    private static List<String> recentDetails;
    private static List<Integer> numbers;
    public static void initializer(){
        names=new ArrayList<>();
        fields=new ArrayList<>();
        prevDetails=new ArrayList<>();
        recentDetails=new ArrayList<>();
        numbers=new ArrayList<>();
    }
    public static void addNames(String name){names.add(name);}
    public static void addFields(String s){fields.add(s);}
    public static void addPrevDetails(String s){prevDetails.add(s);}
    public static void addRecentDetails(String s){recentDetails.add(s);}
    public static void addNumbers(int a){numbers.add(a);}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<names.size();i++) {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane a=new AnchorPane();
            loader.setLocation(getClass().getResource("/sample/ForumHeader.fxml"));
            try {
                a = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ForumHeaderController forumHeaderController = loader.getController();
            forumHeaderController.setData("ANNOUNCEMENT - " + (i + 1));
            vbox.getChildren().add(a);


            FXMLLoader loader3 = new FXMLLoader();
            AnchorPane c=new AnchorPane();
            loader3.setLocation(getClass().getResource("/sample/ForumItem.fxml"));
            try {
                c = loader3.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ForumItemController forumItemController = loader3.getController();
            forumItemController.setData("Name","Number","Field","Previous_Info","Updated_Info");
            vbox.getChildren().add(c);




            FXMLLoader loader2 = new FXMLLoader();
            AnchorPane b=new AnchorPane();
            loader2.setLocation(getClass().getResource("/sample/ForumItem.fxml"));
            try {
                b = loader2.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            forumItemController = loader2.getController();
            forumItemController.setData(names.get(i),String.valueOf(numbers.get(i)),fields.get(i),prevDetails.get(i),recentDetails.get(i));
            vbox.getChildren().add(b);
        }

    }
    public void queriesAction()
    {
        try {
            main.showQueries();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setMain(Main main)
    {
        this.main=main;
    }
    @FXML
    void homeAction(ActionEvent event) {
        try {
            main.showHome();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void statisticsAction(ActionEvent event)
    {
        try {
            main.showStatistics();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void searchAction(ActionEvent event)
    {
        try {
            main.showSearch();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void logoutAction(ActionEvent event)
    {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
