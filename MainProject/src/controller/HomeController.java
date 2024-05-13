package controller;


import data.FileOperationsImage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import sample.Main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private Main main;
    @FXML
    private Label longText;
    @FXML
    private Button logout;
    @FXML
    private Label message;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView image;
    @FXML
    private Button forum;
    @FXML
    private Button button;
    @FXML
    private Button search;
    @FXML
    private Button queries;
    @FXML
    private Button statistics;
    @FXML
    private Button market;
    @FXML
    private StackPane stack;

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
    public void marketAction()
    {
        try {
            main.showMarket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void logoutAction(ActionEvent event)
    {
        try {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void forumAction(ActionEvent event)
    {
        try {
            main.showForum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        longText.setText("Welcome to\n"+Main.getClientName()+"\n"+"with "+Main.getPlayersListGlobal().size()+" players from different countries !!!"
        +" The club has a rich heritage over the world. Explore the amazing Players of it.");
        FileInputStream input = null;
        try {
            input = new FileInputStream("FirstPageImage.jpg");
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

        try {
            FileInputStream input1 = new FileInputStream("new11.png");
            Image image1 = new Image(input1);
            FileInputStream input2 = new FileInputStream("newf2.png");
            Image image2 = new Image(input2);
            FileInputStream input3 = new FileInputStream("new22.png");
            Image image3 = new Image(input3);
            FileInputStream input4 = new FileInputStream("newf1.png");
            Image image4 = new Image(input4);


            ImageView imageView1=new ImageView(image1);
            imageView1.setFitHeight(100);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitHeight(100);
            ImageView imageView3=new ImageView(image3);
            imageView3.setFitHeight(100);
            ImageView imageView4=new ImageView(image4);
            imageView3.setFitHeight(100);
            stack.setBackground(background);
            Timeline t=new Timeline();
            t.setCycleCount(Animation.INDEFINITE);
            t.getKeyFrames().add(new KeyFrame(Duration.millis(500), e->{
                stack.getChildren().setAll(imageView1);
                stack.setAlignment(imageView1, Pos.BOTTOM_CENTER);
            }));
            t.getKeyFrames().add(new KeyFrame(Duration.millis(800),e->{
                stack.getChildren().setAll(imageView2);
                stack.setAlignment(imageView2,Pos.BOTTOM_CENTER);
            }));
            t.getKeyFrames().add(new KeyFrame(Duration.millis(1100),e->{
                stack.getChildren().setAll(imageView3);
                stack.setAlignment(imageView3,Pos.BOTTOM_CENTER);
            }));
            t.getKeyFrames().add(new KeyFrame(Duration.millis(1400),e->{
                stack.getChildren().setAll(imageView4);
                stack.setAlignment(imageView4,Pos.BOTTOM_CENTER);
            }));
            t.play();

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}



