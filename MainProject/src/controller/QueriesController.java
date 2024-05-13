package controller;

import data.Players;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueriesController implements Initializable {
    private Main main;
    @FXML
    private VBox vbox;
    @FXML
    private Button forum;
    private static List<List<String>>total_Fields;
    private static List<List<String>>total_details;

    public static List<String> getTotal_times() {
        return total_times;
    }

    private static List<String>total_times;
    private static List<List<Players>>Found_List;

    public static   List<List<String>> getTotal_Fields(){
        return total_Fields;
    }
    public static List<List<String>> getTotal_details(){
        return total_details;
    }
    public static List<List<Players>>getFound_List(){
        return Found_List;
    }
    public static void addTotalFields(List<String>string)
    {
        total_Fields.add(string);
    }
    public static void addTotalDetails(List<String>string){
        total_details.add(string);
    }
    public static void addTotalTimes(String string){
        total_times.add(string);
    }
    public static void addFoundList(List<Players>players)
    {
        Found_List.add(players);
        for(int i=0;i<Found_List.size();i++)
        {
            List<Players>p=Found_List.get(i);
            System.out.println(p.size());
        }
    }

    public static void initializer()
    {
        total_details=new ArrayList<List<String>>();
        total_Fields=new ArrayList<List<String>>();
        Found_List=new ArrayList<List<Players>>();
        total_times=new ArrayList<String>();

    }
    @FXML
    private Button home;

    @FXML
    private Button queries;
    @FXML
    private Button statistics;
    @FXML
    private Button search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button button[]=new Button[Found_List.size()];
        for(int i=0;i<Found_List.size();i++) {
            button[i] = new Button("Download");
            AnchorPane a = new AnchorPane();
            AnchorPane b = new AnchorPane();
            AnchorPane c = new AnchorPane();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/HeaderQuery.fxml"));
            try {
                a = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HeaderQueryController headerQueryController = loader.getController();
            headerQueryController.setData("QUERIES - " + (i + 1), total_times.get(i), "Search-Fields");
            vbox.getChildren().add(a);
            List<String> list1 = total_Fields.get(i);
            List<String> list2 = total_details.get(i);
            for (int j = 0; j < list1.size(); j++) {
                FXMLLoader loader1 = new FXMLLoader();
                loader1.setLocation(getClass().getResource("/sample/FieldQuery.fxml"));
                try {
                    b = loader1.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FieldQueryController fieldQueryController = loader1.getController();
                fieldQueryController.setData(list1.get(j), list2.get(j));
                vbox.getChildren().add(b);
            }

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/sample/QueryPlayer.fxml"));
            try {
                c = loader2.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            QueryPlayerController queryPlayerController = loader2.getController();
            queryPlayerController.setData("Name", "Country", "Club", "Position", "Age", "Height", "Salary", "Number");
            vbox.getChildren().add(c);

            List<Players> list = Found_List.get(i);
            for (int z = 0; z < list.size(); z++) {
                Players p = list.get(z);
                FXMLLoader loader3 = new FXMLLoader();
                loader3.setLocation(getClass().getResource("/sample/QueryPlayer.fxml"));
                try {
                    c = loader3.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                QueryPlayerController queryPlayerController2 = loader3.getController();
                queryPlayerController2.setData(p.GetName(), p.GetCountry(), p.GetClub(), p.GetPosition(), String.valueOf(p.GetAge()), String.valueOf(p.GetHeight()), String.valueOf(p.GetSalary()), String.valueOf(p.GetAge()));
                vbox.getChildren().add(c);
            }
            vbox.getChildren().add(button[i]);
        }
            for(int i2=0;i2<Found_List.size();i2++)
            {
                final int ii=i2;
                button[ii].setOnAction(event -> {
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showSaveDialog(new Stage());

                    if (file != null) {
                        System.out.println(file.getAbsolutePath());
                        saveTextToFile(ii+1,Found_List.get(ii), file);
                    }
                });
            }

    }

    private static void saveTextToFile(int a,List<Players>playersList, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println("Query Number : "+a);
            for(int i=0;i<playersList.size();i++)
            {
                String name=playersList.get(i).GetName();
                String country =playersList.get(i).GetCountry();
                String clubName=playersList.get(i).GetClub();
                String position=playersList.get(i).GetPosition();
                String age=String.valueOf(playersList.get(i).GetAge());
                String number=String.valueOf(playersList.get(i).GetNumber());
                String height=String.valueOf(playersList.get(i).GetHeight());
                String salary=String.valueOf(playersList.get(i).GetSalary());
                writer.println("Name: "+name+", Country: "+country+", Position: "+position+", Club: "+clubName+", Age: "+age+", Height: "+height+", Salary"+salary+", Number: "+number);
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setMain(Main main){
        this.main=main;
    }
    @FXML
    void searchAction(ActionEvent event) {
        try {
            main.showSearch();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void queriesAction(ActionEvent event) {
        try {
            main.showQueries();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void statisticsAction(ActionEvent event) {
        try {
            main.showStatistics();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void homeAction(ActionEvent event) {
        try {
            main.showHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void forumAction(ActionEvent event) {
        try {
            main.showForum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
