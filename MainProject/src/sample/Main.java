package sample;

import client.Client;
import controller.*;
import dto.SendAllList;
import dto.ToClientPlayerList;
import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import data.*;
import javafx.util.Duration;
import util.NetworkUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class Main extends Application {
    public static List<Players> getPlayersListGlobal() {
        return playersListGlobal;
    }

    public static void setPlayersListGlobal(List<Players> playersListGlobal) {
        Main.playersListGlobal = playersListGlobal;
        FileInputStream input = null;
        try {
            input = new FileInputStream("DefaultImage.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter writer = writableImage.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                writer.setColor(x, y, color);
            }
        }
        for(int i=0;i<playersListGlobal.size();i++) {
            if(playersListGlobal.get(i).getPictureState()==false) {
                playersListGlobal.get(i).setImage(writableImage, 2);
            }
        }
    }

    public static List<Players>playersListGlobal=new ArrayList<>();
    public static List<String>buyClubs=new ArrayList<>();
    public static List<String>buyNumbers=new ArrayList<>();
    static SearchFinal  controller;
    public static String clientName;
    public static Client client;
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public  static void setClientName(String name)
    {
        clientName=name;
    }
    public static String getClientName(){return clientName;}
    public static Stage getStage() {
        return stage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showLoginPage();
    }
    public Scene getScene(){return scene;}
    public void setScene(Scene scene){this.scene=scene;}

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setMain(this);
        ((AnchorPane)root).getChildren().add(controller.getText());
        scene=new Scene(root,850,600);
        FileInputStream input = new FileInputStream("loginPicture.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundimage);
        controller.getMainRoot().setBackground(background);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void showSearch() throws Exception{
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.setMain(this);
        Scene scene=new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public void showForum() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Forum.fxml"));
        Parent root = loader.load();
        ForumController forumController=loader.getController();
        forumController.setMain(this);
        Scene scene=new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
    }
    public void showMarket() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Market.fxml"));
        Parent root = loader.load();
        MarketController controller = loader.getController();
        controller.setMain(this);
        Scene scene=new Scene(root,800,650);
        stage.setScene(scene);
        stage.show();
        Timeline timeline=new Timeline(new KeyFrame(Duration.millis(5000), event -> {
            try {
                Main.getClient().getNetworkUtil().write("sendPlayersList");
                Object o=Main.getClient().getNetworkUtil().read();
                if(o instanceof ToClientPlayerList)
                {
                    if(!(((ToClientPlayerList)o).getPlayersList().size()==MarketController.getP().size()))
                    {
                        stage.close();
                        showMarket();}
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
    public static SearchFinal getSearchFinal()
    {
        return controller;
    }
    public  void showQueries() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FinalQuery.fxml"));
        Parent root = loader.load();
        QueriesController controller=loader.getController();
        controller.setMain(this);
        Scene scene=new Scene(root,900,630);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
    public  void showStatistics() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("chart.fxml"));
        Parent root = loader.load();
        ChartController controller=loader.getController();
        controller.setMain(this);
        controller.setData();
        Scene scene=new Scene(root,850,650);
        stage.setScene(scene);
        stage.show();

    }
    public void showHome() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        controller.setMain(this);
        Scene scene=new Scene(root,880,600);
        stage.setScene(scene);
        stage.show();

        Timeline timeline2=new Timeline(new KeyFrame(Duration.millis(5000), event -> {
            try {
                NetworkUtil networkUtil=Main.getClient().getNetworkUtil();

                networkUtil.write("PlayerListofClub");
                networkUtil.write(Main.getClientName());
                Object o1=networkUtil.read();
                List<Players>playersList;
                if(o1 instanceof SendAllList)
                {
                    SendAllList sendAllList=(SendAllList) o1;
                    playersList=sendAllList.getPlayersList();
                    Main.setPlayersListGlobal(playersList);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.playFromStart();

    }
    public static Client getClient()
    {
        return client;
    }
    public static void main(String[] args) {
       QueriesController.initializer();
       ForumController.initializer();
       String serverAddress = "127.0.0.1";
       int serverPort = 33333;
       client = new Client(serverAddress,serverPort);
       launch(args);
    }
}
