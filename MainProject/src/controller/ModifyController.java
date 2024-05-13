package controller;

import data.Players;
import data.Queries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class ModifyController {
    private Main main;
    private Players players;
   @FXML
    ImageView imageView;
   @FXML
    Hyperlink hyperlink;
   @FXML
    TextField name_text;
    @FXML
     TextField country_text;
    @FXML
    TextField position_text;
    @FXML
    TextField age_text;
    @FXML
   TextField height_text;
    @FXML
    TextField salary_text;
    @FXML
    TextField number_text;
    @FXML
   Button save;
    @FXML
    private VBox vbox;

    private static ErrorController errorController;

    public static Stage getErrorStage() {
        return errorStage;
    }

    private static Stage errorStage=new Stage();

    @FXML
    public  void Modify(Players p)
    {
        players=p;
        name_text.setText(p.GetName());
        country_text.setText(p.GetCountry());
        position_text.setText(p.GetPosition());
        age_text.setText(String.valueOf(p.GetAge()));
        height_text.setText(String.valueOf(p.GetHeight()));
        salary_text.setText(String.valueOf(p.GetSalary()));
        number_text.setText(String.valueOf(p.GetNumber()));
        imageView.setImage(p.getImage());
    }
    public void saveAction(ActionEvent event)
    {
        int yes=1;
        int flag=0;
        if(Queries.check(name_text.getText().trim(),0)==true){
        if(!(players.GetName().equalsIgnoreCase(name_text.getText())))
        {
            ForumController.addFields("Name");
            ForumController.addPrevDetails(players.GetName());
            ForumController.addRecentDetails(name_text.getText());
            players.SetName(name_text.getText());
            flag=1;
        }}else{yes=0;}
        if(Queries.check(country_text.getText().trim(),0)==true) {
        if(!(players.GetCountry().equalsIgnoreCase(country_text.getText())))
        {
            ForumController.addFields("Country");
            ForumController.addPrevDetails(players.GetCountry());
            ForumController.addRecentDetails(country_text.getText());
            players.SetCountry(country_text.getText());
            flag=1;
        }}else{yes=0;}
        if(Queries.check(position_text.getText().trim(),1)==true) {
            if(!(players.GetPosition().equalsIgnoreCase(position_text.getText())))
        {
            ForumController.addFields("Position");
            ForumController.addPrevDetails(players.GetPosition());
            ForumController.addRecentDetails(position_text.getText());
            players.SetPosition(position_text.getText());
            flag=1;
        }}else{yes=0;}
        if(Queries.check(age_text.getText().trim(),2)==true) {
        if(!(players.GetAge()==(Integer.parseInt(age_text.getText().trim()))))
        {
            ForumController.addFields("Age");
            ForumController.addPrevDetails(String.valueOf(players.GetAge()));
            ForumController.addRecentDetails(age_text.getText());
            players.SetAge(Integer.valueOf(age_text.getText().trim()));
            flag=1;
        }}else{yes=0;}
        if(Queries.check(height_text.getText().trim(),3)==true) {
        if(!(players.GetHeight()==(Double.parseDouble(height_text.getText().trim()))))
        {
            ForumController.addFields("Height");
            ForumController.addPrevDetails(String.valueOf(players.GetHeight()));
            ForumController.addRecentDetails(height_text.getText());
            players.SetHeight(Double.valueOf(height_text.getText().trim()));
            flag=1;
        }}else{yes=0;}
        if(Queries.check(number_text.getText().trim(),2)==true) {
        if(!(players.GetNumber()==(Integer.parseInt(number_text.getText().trim()))))
        {
            ForumController.addFields("Number");
            ForumController.addPrevDetails(String.valueOf(players.GetHeight()));
            ForumController.addRecentDetails(number_text.getText());
            players.SetNumber(Integer.valueOf(number_text.getText().trim()));
            flag=1;
        }}else{yes=0;}
        if(Queries.check(salary_text.getText().trim(),3)==true) {
        if(!(players.GetSalary()==(Double.parseDouble(salary_text.getText().trim()))))
        {
            ForumController.addFields("Salary");
            ForumController.addPrevDetails(String.valueOf(players.GetSalary()));
            ForumController.addRecentDetails(salary_text.getText());
            players.SetSalary(Double.valueOf(salary_text.getText().trim()));
            flag=1;
        }}else{yes=0;}

            if(yes==0)
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
                errorController.getLabel().setText("Some Information is in invalid Format.");
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
            if(yes==1 && flag==1) {
                refresh2();
                ForumController.addNames(players.GetName());
                ForumController.addNumbers(players.GetNumber());
            }

    }
    public void refresh2()
    {
       SearchFinal searchFinal=Main.getSearchFinal();
       searchFinal.getStagemy().close();
       List<Players> playersList=searchFinal.getResultList();
       searchFinal.F2(playersList);
    }
    @FXML
    public void hyperlinkAction(ActionEvent event)
    {
        FileChooser fc=new FileChooser();
          File selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                System.out.println(selectedFile.getAbsolutePath());
                Image image = null;
                try {
                    image = new Image(selectedFile.toURI().toURL().toExternalForm());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                int width = (int) image.getWidth();
                int height = (int) image.getHeight();
                WritableImage writableImage = new WritableImage(width, height);
                PixelReader pixelReader = image.getPixelReader();

                PixelWriter writer = writableImage.getPixelWriter();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        Color color = pixelReader.getColor(x, y);
                        double red = color.getRed();
                        double blue = color.getBlue();
                        double green = color.getGreen();
                        Color color2 = Color.color(red, green, blue);
                        writer.setColor(x, y, color2);
                    }
                }
                ImageView myimageView = new ImageView(writableImage);
                myimageView.setImage(writableImage);
                players.setImageView(imageView);
                players.setImage(writableImage,2);
                refresh2();
                refresh3();
            } else
                System.out.println("oops");
    }

    private void refresh3() {
        ResultController.getStageModify().close();
        ResultController.getControllerM().Modify(players);
        ResultController.getStageModify().show();
    }


    public void setMain(Main main) {
        this.main = main;
    }


    public VBox  getMainRoot() {
        return vbox;
    }
}
