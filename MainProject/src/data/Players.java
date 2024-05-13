package data;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Players  implements Serializable {
    private  boolean statePicture;

    public boolean isStatePictureDefault() {
        return statePictureDefault;
    }

    public void setStatePictureDefault(boolean statePictureDefault) {
        this.statePictureDefault = statePictureDefault;
    }

    private  boolean statePictureDefault;
    private int age;
    private double height;
    private int number;
    private double salary;
    private String name;
    private String country;
    private String club;
    private String position;
    private List<Color>colorList;
    private int width_picture;

    public void setStatePicture(boolean statePicture) {
        this.statePicture = statePicture;
    }

    private ImageView imageView;
    private Image image;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.statePicture=true;
    }

    public Players(Players players) {
        this.name=players.name;
        this.position=players.position;
        this.country=players.country;
        this.age=players.age;
        this.height=players.height;
        this.salary=players.salary;
        this.number= players.number;
    }

    public void setPicture(boolean b){this.statePicture=b;}
    public void SetAge(int age)
    {
        this.age=age;
    }
    public void SetHeight(double height)
    {
        this.height=height;
    }
    public void SetNumber(int number)
    {
        this.number=number;
    }
    public void SetSalary(double salary)
    {
        this.salary=salary;
    }
    public void SetName(String name)
    {
        this.name=name;
    }
    public void SetCountry(String country)
    {
        this.country=country;
    }
    public void SetClub(String club)
    {
        this.club=club;
    }
    public void SetPosition(String position)
    {
        this.position=position;
    }
    public int GetAge()
    {
        return age;
    }
    public double GetHeight()
    {
        return height;
    }
    public int GetNumber()
    {
        return number;
    }
    public double GetSalary()
    {
        return salary;
    }
    public String GetName()
    {
        return name;
    }
    public String GetCountry()
    {
        return country;
    }
    public String GetClub()
    {
        return club;
    }
    public String GetPosition()
    {
        return position;
    }
    public Players()
    {
    }
    public Players(String name,String country, String position, String club,int age,int number,double height,double salary){
        this.name=name;
        this.country=country;
        this.club=club;
        this.position=position;
        this.age=age;
        this.salary=salary;
        this.number=number;
        this.height=height;
        this.statePicture=false;
        this.statePictureDefault=true;
    }
    public void print()
    {
        System.out.println("Name:"+name+" Country:"+country+" Age:"+age+" Height:"+height+" Club:"+club+" Position:"+position+" Number:"+number+" Weekly Salary:"+salary);
    }
    public List<Players> SearchByName(String name, List<Players> players)
    {
        List<Players>players2=new ArrayList();
        if(name.equalsIgnoreCase("any"))
        {
            for(int i=0;i<players.size();i++)
            {
                Players p=players.get(i);

                    players2.add(p);

            }
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                Players p = players.get(i);
                if (name.equalsIgnoreCase(p.GetName())) {
                    players2.add(p);
                }
            }
        }
        return players2;
    }
    public List<Players> SearchByCountry(String name, List<Players> players)
    {
        List<Players>players2=new ArrayList();
        if(name.equalsIgnoreCase("any"))
            return players;
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if(name.equalsIgnoreCase(p.GetCountry()))
            {
                players2.add(p);
            }
        }
        return players2;
    }
    public List<Players> SearchByAge(int age, List<Players> players)
    {
        List<Players>players2=new ArrayList();
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if(age==p.GetAge())
            {
                players2.add(p);
            }
        }
        return players2;
    }
    public List<Players> SearchByNumber(int number, List<Players> players)
    {
        List<Players>players2=new ArrayList();
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if(number==p.GetNumber())
            {
                players2.add(p);
            }
        }
        return players2;
    }
    public List<Players> SearchByClub(String name, List<Players> players)
    {

        List<Players>players2=new ArrayList();
        if(name.equalsIgnoreCase("any"))
            return players;
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if(name.equalsIgnoreCase(p.GetClub()))
            {
                players2.add(p);
            }
        }
        return players2;
    }
    public List<Players> SearchByTwo(String clubName,String countryName, List<Players> players)
    {
        List<Players>players2=new ArrayList();
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if(clubName.equalsIgnoreCase(p.GetClub()) && countryName.equalsIgnoreCase(p.GetCountry()))
            {
                players2.add(p);
            }
        }
        return players2;
    }
    public List<Players>  SearchByPosition(String position, List<Players> players)
    {
        List<Players>players2=new ArrayList();
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if(position.equalsIgnoreCase(p.GetPosition()))
            {
                players2.add(p);
            }
        }
        return players2;
    }
    public  List<Players> SearchBySalary(int a, int b, List<Players> players) {
        List<Players>players2=new ArrayList();
        for(int i=0;i<players.size();i++)
        {
            Players p=players.get(i);
            if((a<=p.GetSalary()) && (b>=(p.GetSalary())))
            {
                players2.add(p);
            }
        }
        return players2;
    }

    public boolean getPictureState() {
        return statePicture;
    }

    public void setImage(WritableImage writableImage,int a) {
        image=writableImage;
        if(a==1) {
            statePicture = true;
            statePictureDefault = false;
        }
        else {
            statePictureDefault = true;
            statePicture = false;
        }
    }

    public Image getImage() {
        return image;
    }

    public List<Players> SearchByHeight(String input, List<Players> playersList) {
        List<Players>players2=new ArrayList();
        for(int i=0;i<playersList.size();i++)
        {
            Players p=playersList.get(i);
            if((Double.parseDouble(input)==p.GetHeight()))
            {
                players2.add(p);
            }
        }
        return players2;
    }
}




