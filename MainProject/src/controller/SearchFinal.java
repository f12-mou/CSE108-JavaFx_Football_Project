package controller;

import data.Players;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import data.*;

public class SearchFinal {
    private Main main;
        @FXML
        private Button home;
    @FXML
    private Button queries;

    @FXML
    private Hyperlink name;
    @FXML
    private Hyperlink country;
    @FXML
    private Hyperlink position;
    @FXML
    private Hyperlink age;
    @FXML
    private Hyperlink salary;
    @FXML
    private Hyperlink height;
    @FXML
    private Hyperlink number;
    @FXML
    private Button statistics;
    @FXML
    private Button forum;
   @FXML
   private VBox vbox1;

    public VBox getVbox2() {
        return vbox2;
    }

    public void setVbox2(VBox vbox2) {
        this.vbox2 = vbox2;
    }

    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    @FXML
    private Label lbl;
    @FXML
    private TextField txtfield;
    @FXML
    private Button option ;
    @FXML
    private Button logout;
    @FXML
    private Button submit;
    private Button[]button;
    private Hyperlink[] sell;
    private static Stage stagemy=new Stage();
    private List<Players>resultList;

    private int vis[];
    private String input;
    private List<String>input_Fields_name;
    private List<String>input_Fields_details;
    private List<Integer>input_Fields_numbers;
    private String string[]={" ","Name","Country","Position","Age","Salary","Height","Number"};
    private static ErrorController errorController;

    public static Stage getErrorStage() {
        return errorStage;
    }

    private static Stage errorStage=new Stage();

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
            e.printStackTrace();
        }
    }
    @FXML
    void forumAction(ActionEvent event)
    {
        try {
            main.showForum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logoutAction(ActionEvent event)
    {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nameAction(ActionEvent event){
        if(vis[1]==0)
        {
            setVis(1);
            lbl.setText("Enter the Name of the Player: ");
            name.setVisited(false);
            function();
        }
    }
    public void countryAction(ActionEvent event){
        if(vis[2]==0)
        {
            setVis(2);
            lbl.setText("Enter the Country of the Player: ");
            country.setVisited(false);
            function();
        }
    }
    public void positionAction(ActionEvent event){
        if(vis[3]==0)
        {
            setVis(3);
            lbl.setText("Enter the Position of the Player: ");
            position.setVisited(false);
            function();
        }
    }
    public void ageAction(ActionEvent event){
        if(vis[4]==0)
        {
            setVis(4);
            lbl.setText("Enter the Age of the Player: ");
            age.setVisited(false);
            function();
        }
    }
    public void salaryAction(ActionEvent event){
        if(vis[5]==0)
        {
            setVis(5);
            lbl.setText("Enter the Salary of the Player: ");
            salary.setVisited(false);
            function();
        }
    }
    public void heightAction(ActionEvent event){
        if(vis[6]==0)
        {
            setVis(6);
            lbl.setText("Enter the Height of the Player: ");
            height.setVisited(false);
            function();
        }
    }
    public void numberAction(ActionEvent event){
        if(vis[7]==0)
        {
            setVis(7);
            lbl.setText("Enter the Number of the Player: ");
            number.setVisited(false);
            function();
        }
    }
    public void setOption(ActionEvent event)
    {
        inverseFunction();
        submit.setVisible(true);
        for(int i=1;i<8;i++)
        {
            if(vis[i]==1)
            {
                input_Fields_numbers.add(i);
                input_Fields_name.add(string[i]);
                input_Fields_details.add(input);
                Label label_field=new Label(string[i]+": "+input);
                label_field.setStyle("-fx-text-fill: Yellow; -fx-font-size:15;");
                vbox3.getChildren().add(label_field);
                vis[i]=0;
                input="";
                txtfield.setText("");
                break;
            }
        }
    }
    public void textAction(ActionEvent e)
    {
       input=(txtfield.getText());
    }
    public void setMain(Main main) {
        this.main = main;
        vis=new int[8];
        inverseFunction();
        input_Fields_numbers=new ArrayList();
        input_Fields_details=new ArrayList();
        input_Fields_name=new ArrayList();
    }
    public void function()
    {
        txtfield.setVisible(true);
        option.setVisible(true);
        submit.setVisible(true);
        lbl.setVisible(true);
    }
    public void inverseFunction()
    {
        txtfield.setVisible(false);
        option.setVisible(false);
        submit.setVisible(false);
        lbl.setVisible(false);
    }
    public void setVis(int a){
        for(int i=0;i<8;i++)
        {
            vis[i]=0;
        }
        vis[a]=1;
    }
    @FXML
    void submitAction(ActionEvent event) {

        inverseFunction();
        int yes=1;
        for(int i=1;i<8;i++)
        {
            if(vis[i]==1)
            {
                input_Fields_numbers.add(i);
                input_Fields_name.add(string[i]);
                input_Fields_details.add(input);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String str=dateFormat.format(cal.getTime());
                QueriesController.addTotalTimes(str);
                Label label_field=new Label(string[i]+": "+input);
                label_field.setStyle("-fx-text-fill: Yellow; -fx-font-size: 15;");
                Label blank=new Label("");
                vbox3.getChildren().addAll(label_field,blank);
                vis[i]=0;
                txtfield.setText("");
                break;
            }
        }
        List<Players> players;
        List<Players> players2;
        List<Players> players_final=new ArrayList<>();
        List<Players>playersList= Main.getPlayersListGlobal();
        Players p=new Players();
        int count=1;
        int vis2[]=new int[8];
        vis2[0]=vis2[1]=vis2[2]=vis2[3]=vis2[4]=vis2[5]=vis2[6]=vis2[7]=0;

        for(int i=0;i<input_Fields_numbers.size();i++) {
            int a=input_Fields_numbers.get(i);
            vis2[a]=1;
            input=input_Fields_details.get(i);
            if (vis2[1] == 1) {
                if (Queries.check(input.trim(), 0) == true) {
                    if (count < 2) {
                        players = p.SearchByName(input, playersList);
                        players_final = players;
                        System.out.println(players_final.size());
                    } else {
                        players2 = p.SearchByName(input, playersList);
                        players_final = commonCheck(players_final, players2);
                    }
                    count++;
                } else {
                    yes = 0;
                }
            }
            if (vis2[2] == 1) {
                if(Queries.check(input.trim(),0)==true) {

                    if (count < 2) {
                        players = p.SearchByCountry(input.trim(), playersList);
                        players_final = players;
                    } else {
                        players2 = p.SearchByCountry(input.trim(), playersList);
                        players_final = commonCheck(players_final, players2);
                    }
                    count++;

                  }else yes=0;
            }
            if (vis2[3] == 1) {
                if(Queries.check(input.trim(),1)==true) {
                    if (count < 2) {
                        players = p.SearchByPosition(input.trim(), playersList);
                        players_final = players;
                    } else {

                        players2 = p.SearchByPosition(input.trim(), playersList);
                        players_final = commonCheck(players_final, players2);
                    }
                    count++;
                }else yes=0;
            }
            if (vis2[4] == 1) {
                if(Queries.check(input.trim(),2)==true) {
                if (count < 2) {
                    players = p.SearchByAge(Integer.parseInt(input), playersList);
                    players_final = players;
                } else {
                    players2 = p.SearchByAge(Integer.parseInt(input), playersList);
                    players_final = commonCheck(players_final, players2);
                }
                    count++;
                }else yes=0;
            }

            if (vis2[5] == 1) {
                if(Queries.check(input.trim(),3)==true) {
                    System.out.println(input+" "+Queries.check(input.trim(),3));
                if (count < 2) {
                    players = p.SearchBySalary(0, Integer.parseInt(input), playersList);
                    players_final = players;
                } else {
                    players2 = p.SearchBySalary(0, Integer.parseInt(input), playersList);
                    players_final = commonCheck(players_final, players2);
                }
                    count++;
                }else yes=0;
            }
            if (vis2[6] == 1) {
                if(Queries.check(input.trim(),3)==true) {
                if (count < 2) {
                    players = p.SearchByHeight(input, playersList);
                    players_final = players;
                    System.out.println(players_final.size());
                } else {
                    players2 = p.SearchByHeight(input, playersList);
                    players_final = commonCheck(players_final, players2);
                }
                    count++;
                }else yes=0;
            }
            if (vis2[7] == 1) {
                if(Queries.check(input.trim(),2)==true) {
                if (count < 2) {
                    players = p.SearchByNumber(Integer.parseInt(input), playersList);
                    players_final = players;
                } else {
                    players2 = p.SearchByNumber(Integer.parseInt(input), playersList);
                    players_final = commonCheck(players_final, players2);
                }
                    count++;
                }else yes=0;
            }
            vis2[a]=0;
        }
        for(int i=0;i<players_final.size();i++)
        {
           if(players_final.get(i)!=null)
               players_final.get(i).print();
           else
               break;
        }

        QueriesController.addTotalFields(input_Fields_name);
        QueriesController.addTotalDetails(input_Fields_details);
        QueriesController.addFoundList(players_final);
        input="";
        input_Fields_numbers=new ArrayList();
        for(int z=0;z<vis.length;z++)
            vis[z]=0;
        resultList=players_final;
        F2(players_final);
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


    }
    public void F2(List<Players>players)
    {
        Players p=new Players();
        Parent root=null;
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/ResultView.fxml"));
        try {
             root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResultController resultController=loader.getController();
        resultController.setData( input_Fields_details,input_Fields_name);
        input_Fields_details=new ArrayList<>();
        input_Fields_name=new ArrayList<>();
        resultController.setData3(players);
        Scene scene=new Scene(root,800,500);
        stagemy.setScene(scene);
        stagemy.show();

    }

    public List<Players> getResultList()
    {
        return resultList;
    }
    public Stage getStagemy()
    {
        return stagemy;
    }

    public List<Players>commonCheck(List<Players>p1,List<Players>p2)
    {
        if(p1.size()==0)
            return p2;
        if(p2.size()==0)
            return p1;
        int size=p1.size();
        for(int i=0;i<size;i++)
        {
            Players p=p1.get(i);
            int flag=0;
            for(int j=0;j<p2.size();j++)
            {
                if(p.GetName().equalsIgnoreCase(p2.get(j).GetName())) { flag=1;break; }
            }
            if(flag==0) {
                p1.remove(p);
                size--;
                i--;
            }
        }
        return p1;
    }
    public void queriesAction()
    {
        try {
            main.showQueries();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
