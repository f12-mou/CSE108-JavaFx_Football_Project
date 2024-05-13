package data;
import java.util.ArrayList;
import java.util.List;

public class Country {
    private int playerCount;
    String name;
    List<Clubs>clubs;
    public Country()
    {
        clubs=new ArrayList();
    }
    public Country(String s)
    {
        clubs=new ArrayList();
        name=s;
    }
    public String GetName()
    {
        return name;
    }
    public int GetClubCount(){
        return clubs.size();
    }
    public void AddClubs(Clubs c,Players p)
    {
        clubs.add(new Clubs(c.GetName()));
        (clubs.get(clubs.size()-1)).addPlayers(p);
    }
    public int GetPlayerCount()
    {
        if(playerCount ==0) {
            for (int i = 0; i < clubs.size(); i++) {
                playerCount = playerCount + (clubs.get(i)).GetPlayerCount();
            }
        }
        return playerCount;
    }
    public void PrintAllPlayers()
    {
        for(int i=0;i<clubs.size();i++)
        {
            (clubs.get(i)).Print_Players();
        }
    }
}


