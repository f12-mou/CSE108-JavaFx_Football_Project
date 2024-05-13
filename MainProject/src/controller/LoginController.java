package controller;

import data.FileOperationsImage;
import data.Main2;
import data.Players;
import dto.LoginMessage;
import dto.RegisterMessage;
import dto.SendAllList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import util.NetworkUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class LoginController {

    private Main main;

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static RegisterController getRegisterController() {
        return registerController;
    }

    private static RegisterController registerController=new RegisterController();

    public void setSecondaryStage(Stage secondaryStage) {
        this.secondaryStage = secondaryStage;
    }

    private static Stage secondaryStage=new Stage();

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;
    @FXML
    private Button register;

    @FXML
    void registerAction(ActionEvent event) {

        String userName = userText.getText();
        String password = passwordText.getText();

            try {
                RegisterMessage registerMessage=new RegisterMessage();
                registerMessage.setName(userName);
                registerMessage.setPassword(password);
                Main.getClient().getNetworkUtil().write(registerMessage);
                userText.setText("");
                passwordText.setText("");
                Object o= Main.getClient().getNetworkUtil().read();
                int flag=0;
                if(o instanceof String)
                {
                    String str=(String) o;
                    if(str.equalsIgnoreCase("successRegister"))
                        flag=1;
                    else
                        flag=0;
                }
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Register.fxml"));
                Parent root=loader.load();
                registerController=loader.getController();
                FileInputStream input = new FileInputStream("back.jpg");
                Image image = new Image(input);
                BackgroundImage backgroundimage = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false));
                Background background = new Background(backgroundimage);
               registerController.getAnchorpane().setBackground(background);
               if(flag==0)
                   registerController.getRegisterLabel().setText("Sorry, Couldn't Register");
                Scene scene=new Scene(root,500,400);
                secondaryStage.setScene(scene);
                secondaryStage.show();
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        int flag=0;
            try {
                LoginMessage loginMessage=new LoginMessage();
                loginMessage.setName(userName);
                loginMessage.setPassword(password);
                  NetworkUtil networkUtil=Main.getClient().getNetworkUtil();
                  networkUtil.write(loginMessage);
                 Object o=  Main.getClient().getNetworkUtil().read();
                if (o instanceof String) {
                    String s = (String) o;
                    if (s.equals("success")) {
                        flag=1;
                        Main.setClientName(userName);
                        List<Players>playersList=new ArrayList<>();
                        networkUtil.write("PlayerListofClub");
                        networkUtil.write(Main.getClientName());
                        Object o1=networkUtil.read();
                        if(o1 instanceof SendAllList)
                        {
                            SendAllList sendAllList=(SendAllList) o1;
                            playersList=sendAllList.getPlayersList();
                            Main.setPlayersListGlobal(playersList);
                        }

                        FileInputStream input = new FileInputStream("DefaultImage.jpg");
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
                      for(int i=0;i<playersList.size();i++)
                          playersList.get(i).setImage(writableImage,2);

                      main.showHome();
                    } else {
                        flag=0;
                        System.out.println("Login Unsuccessful");
                    }
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/Register.fxml"));
                    Parent root=loader.load();
                    registerController=loader.getController();
                    FileInputStream input = new FileInputStream("back.jpg");
                    Image image = new Image(input);
                    BackgroundImage backgroundimage = new BackgroundImage(image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            new BackgroundSize(1.0, 1.0, true, true, false, false));
                    Background background = new Background(backgroundimage);
                    registerController.getAnchorpane().setBackground(background);
                    if(flag==0)
                        registerController.getRegisterLabel().setText("Incorrect Name or Password");
                    else
                        registerController.getRegisterLabel().setText("Logged In Successfully");
                    Scene scene=new Scene(root,500,400);
                    secondaryStage.setScene(scene);
                    secondaryStage.show();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    public void setMain (Main main){
            this.main = main;
        }
    public AnchorPane getMainRoot(){
        return anchorpane;
    }
    public Text getText()
    {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        Text t = new Text();
        t.setEffect(is);
        t.setX(10);
        t.setY(10);
        t.setText("Football-Player Database");
        t.setFill(Color.ALICEBLUE);
        t.setFont(Font.font(null, FontWeight.BOLD, 60));
        t.setTranslateX(70);
        t.setTranslateY(50);
        return t;
    }
 }





