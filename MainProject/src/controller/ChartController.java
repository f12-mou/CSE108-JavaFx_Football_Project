package controller;

import data.FileOperationsImage;
import data.Main2;
import data.Players;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Main;

import java.util.*;

public class ChartController {
    private Main main;
    @FXML
    private  VBox vbox;
    @FXML
    private Button queries;
    @FXML
    private Button statistics;
    @FXML
    private Button home;
    @FXML
    private Button search;
    @FXML
    private Button forum;
    @FXML
    private Button logout;

    private  PieChart pieChart;
    private  PieChart pieChart1;
    private  PieChart pieChart2;
    private  PieChart pieChart3;
    private  PieChart pieChart4;
    public  void setData()
    {
        List<Players>playersList=Main.getPlayersListGlobal();
        Label label1=new Label("PIECHART-1: Statistics of Age");
        Label label2=new Label("PIECHART-2: Statistics of Salary ");
        Label label3=new Label("PIECHART-3: Statistics of Height");
        Label label4=new Label("PIECHART-4: Statistics of Country");
        Label label5=new Label("PIECHART-5: Statistics of Position");

        label1.setStyle("-fx-text-fill: orangered;-fx-font-family:Cambria;-fx-font-weight: Bold;-fx-font-size: 24");
        label2.setStyle("-fx-text-fill: orangered;-fx-font-family:Cambria;-fx-font-weight: Bold;-fx-font-size: 24");
        label3.setStyle("-fx-text-fill: orangered;-fx-font-family:Cambria;-fx-font-weight: Bold;-fx-font-size: 24");
        label4.setStyle("-fx-text-fill: orangered;-fx-font-family:Cambria;-fx-font-weight: Bold;-fx-font-size: 24;");
        label5.setStyle("-fx-text-fill: orangered;-fx-font-family:Cambria;-fx-font-weight: Bold;-fx-font-size: 24;");

        int mini_age=getMinimum(playersList,1);
        int max_age=getMaximum(playersList,1);
        int mini_salary=getMinimum(playersList,2);
        int max_salary=getMaximum(playersList,2);
        int mini_height=getMinimum(playersList,3);
        int max_height=getMaximum(playersList,3);
       setPiechart(mini_age,max_age,playersList);
       setPiechart1(mini_salary,max_salary,playersList);
       setPiechart2(mini_height,max_height,playersList);
        setPiechart3(playersList);
        setPiechart4(playersList);
        pieChart.setStyle("-fx-pie-label-visible:true;-fx-background-color:pink;-fx-pref-height: 300;-fx-pref-width: 700;-fx-min-height: 300;-fx-min-width: 700;-fx-max-height: 500;-fx-max-width: 700;-fx-background-radius: 50;");
        pieChart1.setStyle("-fx-pie-label-visible:true;-fx-background-color:pink;-fx-pref-height: 300;-fx-pref-width: 700;-fx-min-height: 300;-fx-min-width: 700;-fx-max-height: 500;-fx-max-width: 700;-fx-background-radius: 50;");
        pieChart2.setStyle("-fx-pie-label-visible:true;-fx-background-color:pink;-fx-pref-height: 300;-fx-pref-width: 700;-fx-min-height: 300;-fx-min-width: 700;-fx-max-height: 500;-fx-max-width: 700;-fx-background-radius: 50;");
        pieChart3.setStyle("-fx-pie-label-visible:true;-fx-background-color:pink;-fx-pref-height: 300;-fx-pref-width: 700;-fx-min-height: 300;-fx-min-width: 700;-fx-max-height: 500;-fx-max-width: 700;-fx-background-radius: 50;");
        pieChart4.setStyle("-fx-pie-label-visible:true;-fx-background-color:pink;-fx-pref-height: 300;-fx-pref-width: 700;-fx-min-height: 300;-fx-min-width: 700;-fx-max-height: 500;-fx-max-width: 700;-fx-background-radius: 50;");

        pieChart.setLegendSide(Side.LEFT);
        pieChart1.setLegendSide(Side.LEFT);
        pieChart2.setLegendSide(Side.LEFT);
        pieChart3.setLegendSide(Side.LEFT);
        pieChart4.setLegendSide(Side.LEFT);

        vbox.getChildren().addAll(label1,pieChart,label2,pieChart1,label3,pieChart2,label4,pieChart3,label5,pieChart4);
        ScrollPane scrollPane=new ScrollPane(vbox);
        Scene scene=new Scene(scrollPane,1000,900);
        Stage stage= Main.getStage();
        stage.setScene(scene);
        stage.show();
    }
    public  void setPiechart(int a,int b,List<Players>players)
    {
        PieChart.Data data[]=new PieChart.Data[(b-a+1)];
        pieChart=new PieChart();
        int array[]=new int[b-a+1];
        for(int i=0;i<array.length;i++)
            array[i]=0;
        for(int i=0;i<players.size();i++)
        {
            int z=players.get(i).GetAge();
            array[z-a]+=1;

        }
        for(int i=0;i<array.length;i++)
        {
            data[i]=new PieChart.Data(a+"",array[i]);
            a++;
        }
        ObservableList<PieChart.Data>list= FXCollections.observableArrayList(data);
        pieChart.setData(list);
    }
    public  void setPiechart1(int a,int b,List<Players>players){
        PieChart.Data data[]=new PieChart.Data[(b-a+1)/50000];
        pieChart1=new PieChart();
        int array[]=new int[(b-a+1)/50000];
        for(int i=0;i<array.length;i++)
            array[i]=0;
        for(int i=0;i<players.size();i++)
        {
            int z=(int)players.get(i).GetSalary();
            int temp=a;
            for(int j=0;j<array.length;j++)
            {
                if(z<=(temp+50000) && z>=temp)
                {
                    array[j]+=1;
                    break;
                }
                else
                    temp+=50000;
            }
        }
        for(int i=0;i<array.length;i++)
        {
            data[i]=new PieChart.Data(a+"-"+(a+50000),array[i]);
            a+=50000;
        }
        ObservableList<PieChart.Data>list= FXCollections.observableArrayList(data);
        pieChart1.setData(list);
    }
    public  void setPiechart2(int aa,int bb,List<Players>players){
        if(aa==bb)bb++;
        double a=aa;
        double b=bb;

        PieChart.Data data[]=new PieChart.Data[(bb-aa)*10];
        pieChart2=new PieChart();
        int array[]=new int[(bb-aa)*10];
        for(int i=0;i<array.length;i++)
            array[i]=0;
        for(int i=0;i<players.size();i++)
        {
            double z=players.get(i).GetHeight();
            double temp=a;
            for(int j=0;j<array.length;j++)
            {
                if(z<=(temp+0.1) && z>=temp)
                {
                    array[j]+=1;
                    break;
                }
                else
                    temp+=0.1;
            }
        }
        for(int i=0;i<array.length;i++)
        {
            Formatter formatter = new Formatter();
            formatter.format("%.2f", a);
            Formatter formatter2 = new Formatter();
            formatter.format("%.2f", a+0.1);
            String result = String.format("%.2f - %.2f",a,a+0.1);
            data[i]=new PieChart.Data(result,array[i]);
            a+=0.1;
        }
        ObservableList<PieChart.Data>list= FXCollections.observableArrayList(data);
        pieChart2.setData(list);
    }
    public  void setPiechart3(List<Players>players) {
        pieChart3=new PieChart();
        HashMap<String,Integer>map=new HashMap<>();
        for(int i=0;i<players.size();i++)
        {
            String country=players.get(i).GetCountry();
            map.put(country,0);
        }
        for(int i=0;i<players.size();i++)
        {
            String country=players.get(i).GetCountry();
            map.put(country,map.get(country)+1);
        }
        Iterator<String>iterator=map.keySet().iterator();
        int size=0;
        String[]names=new String[players.size()];
        while(iterator.hasNext())
        {
            names[size]=iterator.next();
            size++;

        }
        PieChart.Data data[]=new PieChart.Data[size];
        for(int i=0;i<size;i++)
        {
            data[i]=new PieChart.Data(names[i],map.get(names[i]));
        }
        ObservableList<PieChart.Data>list= FXCollections.observableArrayList(data);
        pieChart3.setData(list);
    }

    public  void setPiechart4(List<Players>players) {
        pieChart4=new PieChart();
        HashMap<String,Integer>map=new HashMap<>();
        for(int i=0;i<players.size();i++)
        {
            String position=players.get(i).GetPosition();
            map.put(position,0);
        }
        for(int i=0;i<players.size();i++)
        {
            String position=players.get(i).GetPosition();
            map.put(position,map.get(position)+1);
        }
        Iterator<String>iterator=map.keySet().iterator();
        int size=0;
        String[]names=new String[players.size()];
        while(iterator.hasNext())
        {
            names[size]=iterator.next();
            size++;
        }
        PieChart.Data data[]=new PieChart.Data[size];
        for(int i=0;i<size;i++)
        {
            data[i]=new PieChart.Data(names[i],map.get(names[i]));
        }
        ObservableList<PieChart.Data>list= FXCollections.observableArrayList(data);
        pieChart4.setData(list);
    }








        public  int getMinimum(List<Players> players,int identifier)
    {
        int min=90000000;
        for(int i=0;i<players.size();i++)
        {
            if(identifier==1)
            {
                if(min>(players.get(i).GetAge()))
                {
                    min=players.get(i).GetAge();
                }
            }
           else if(identifier==2)
            {
                if(min>(players.get(i).GetSalary()))
                {
                    min=(int)players.get(i).GetSalary();
                }
            }
            else if(identifier==3)
            {
                if(min>(players.get(i).GetHeight()))
                {
                    min=(int)players.get(i).GetHeight();
                }
            }

        }
        return min;
    }

    public  int getMaximum(List<Players> players,int identifier)
    {
        int max=0;
        for(int i=0;i<players.size();i++)
        {
            if(identifier==1)
            {
                if(max<(players.get(i).GetAge()))
                {
                    max=players.get(i).GetAge();
                }
            }
            else if(identifier==2)
            {
                if(max<(players.get(i).GetSalary()))
                {
                    max=(int)players.get(i).GetSalary();
                }
            }
            else if(identifier==3)
            {
                if(max<(players.get(i).GetHeight()))
                {
                    max=(int)players.get(i).GetHeight();
                }
            }

        }
        return max;
    }

    public void setMain(Main main) {
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
    @FXML
    void searchAction(ActionEvent event) {
        try {
            main.showSearch();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    public void queriesAction(ActionEvent event) {
        try {
            main.showQueries();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    public void forumAction(ActionEvent event)
    {
        try {
            main.showForum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
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
}
